package com.spartified.assignment.service;

import com.spartified.assignment.dto.CdrDto;
import com.spartified.assignment.entity.CdrRecordEntity;
import com.spartified.assignment.repository.CdrRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CdrBatchProcessor {

    private static final Logger log = LoggerFactory.getLogger(CdrBatchProcessor.class);

    private final CdrRepository cdrRepository;

    public CdrBatchProcessor(CdrRepository cdrRepository) {
        this.cdrRepository = cdrRepository;
    }

    @Transactional
    public void processBatch(List<CdrDto> batch) {
        List<CdrRecordEntity> entities = new ArrayList<>(batch.size());
        for (CdrDto dto : batch) {
            try {
                CdrRecordEntity entity = toEntity(dto);
                entities.add(entity);
            } catch (Exception e) {
                log.error("Skipping invalid CDR: {} error: {}", dto, e.getMessage());
                // decide whether to skip or fail whole batch; here we skip bad records
            }
        }

        if (!entities.isEmpty()) {
            cdrRepository.saveAll(entities); // JPA batch insert (hbm2ddl settings and jdbc batch help)
            log.info("Saved {} records to DB", entities.size());
        } else {
            log.info("No valid entities to persist in this batch");
        }
    }

    private CdrRecordEntity toEntity(CdrDto dto) {
        CdrRecordEntity e = new CdrRecordEntity();
        e.setMsisdn(dto.getMsisdn());

        // convert bytes to MB (double)
        double mb = dto.getDataVolumeBytes() / 1024.0 / 1024.0;
        // round to 3 decimal places optionally
        e.setDataVolumeMb(Math.round(mb * 1000.0) / 1000.0);

        // parse datetimes - expecting ISO-8601 strings (UTC)
        try {
            Instant startInst = Instant.parse(dto.getStartDatetime());
            Instant endInst = Instant.parse(dto.getEndDatetime());
            e.setStartUtc(startInst);
            e.setEndUtc(endInst);
        } catch (DateTimeParseException ex) {
            // If your system uses epoch millis or other format, adapt parsing.
            throw ex;
        }

        e.setMcc(dto.getMcc());
        e.setMnc(dto.getMnc());
        return e;
    }
}
