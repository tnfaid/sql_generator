package generator.sql.testing.xgety.infrastructure.adapters;

import com.google.gson.Gson;
import generator.sql.testing.xgety.domain.data.FulfillmentScheduleDTO;
import generator.sql.testing.xgety.domain.ports.spi.CampaignProgramPersistencePort;
import generator.sql.testing.xgety.infrastructure.configuration.ConstantConfig;
import generator.sql.testing.xgety.infrastructure.entity.FulfillmentScheduleEntity;
import generator.sql.testing.xgety.infrastructure.entity.QueueCampaignProgram;
import generator.sql.testing.xgety.infrastructure.exception.CampaignProgramException;
import generator.sql.testing.xgety.infrastructure.repository.FulfillmentScheduleRepository;
import generator.sql.testing.xgety.infrastructure.util.CampaignProgramErrorMap;
import generator.sql.testing.xgety.infrastructure.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CampaignProgramAdapter implements CampaignProgramPersistencePort {

    @Autowired
    private final FulfillmentScheduleRepository fulfillmentScheduleRepository;

    @Override
    public Integer insertFulfillmentSchedule(FulfillmentScheduleDTO fulfillmentScheduleDTO) throws CampaignProgramException {
        Integer result = null;
        try {
            FulfillmentScheduleEntity fulfillmentScheduleEntity = new FulfillmentScheduleEntity();
            fulfillmentScheduleEntity.setId(fulfillmentScheduleDTO.getId());
            fulfillmentScheduleEntity.setIdCampaignProgram(fulfillmentScheduleDTO.getIdCampaignProgram());
            fulfillmentScheduleEntity.setTrxKafkaId(fulfillmentScheduleDTO.getTrxKafkaId());
            fulfillmentScheduleEntity.setMsisdn(fulfillmentScheduleDTO.getMsisdn());
            fulfillmentScheduleEntity.setTransactionDate(fulfillmentScheduleDTO.getTransactionDate());
            fulfillmentScheduleEntity.setFulfillmentSchedule(fulfillmentScheduleDTO.getFulfillmentSchedule());
            fulfillmentScheduleEntity.setIncentiveCampaignId(fulfillmentScheduleDTO.getIncentiveCampaignId());
            fulfillmentScheduleEntity.setEventTopicTrigger(fulfillmentScheduleDTO.getEventTopicTrigger());
            fulfillmentScheduleEntity.setCampaignEvent(fulfillmentScheduleDTO.getCampaignEvent());
            fulfillmentScheduleEntity.setMainTrigger(fulfillmentScheduleDTO.getMainTrigger());
            fulfillmentScheduleEntity.setOtherParamTrigger(fulfillmentScheduleDTO.getOtherParamTrigger());
            fulfillmentScheduleEntity.setBonus(fulfillmentScheduleDTO.getBonus());
            fulfillmentScheduleEntity.setCreatedAt(fulfillmentScheduleDTO.getCreatedAt());
            result = fulfillmentScheduleRepository.saveFulfillmentSchedule(fulfillmentScheduleEntity);
        } catch (Exception e) {
            Throwable exceptionNative = Util.lastThrowable(e);
            Util.debugLogger.error("Insert Fulfillment Schedule Failed | Msisdn : {} | sharding {} | {}",fulfillmentScheduleDTO.getMsisdn(), new Gson().toJson(fulfillmentScheduleDTO), exceptionNative.getMessage());
            throw new CampaignProgramException(exceptionNative.getMessage(), CampaignProgramErrorMap.FULFILLMENT_SCHEDULE_SAVE_FAILED);
        }
        return result;
    }
}
