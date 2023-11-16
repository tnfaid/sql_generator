package generator.sql.testing.xgety.infrastructure.repository;

import generator.sql.testing.xgety.infrastructure.configuration.DsConf;
import generator.sql.testing.xgety.infrastructure.entity.FulfillmentScheduleEntity;
import generator.sql.testing.xgety.infrastructure.exception.CampaignProgramException;
import generator.sql.testing.xgety.infrastructure.util.CampaignProgramErrorMap;
import generator.sql.testing.xgety.infrastructure.util.Util;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

@Repository
public class FulfillmentScheduleRepository {
    public int saveFulfillmentSchedule(FulfillmentScheduleEntity fulfillmentScheduleEntity) throws CampaignProgramException {
        String query = "INSERT INTO fulfillment_schedule\n" +
                "(id, id_campaign_program, trx_kafka_id, msisdn, transaction_date, fulfillment_schedule, incentive_campaign_id, event_topic_trigger, campaign_event, main_trigger, other_param_trigger, bonus, created_at)\n" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = 0;
        try (Connection connectionInsert = DsConf.dbConfPes().getConnection();
             PreparedStatement preparedStatmentInsert = connectionInsert.prepareStatement(query)){
            UUID uuid = UUID.randomUUID();
            preparedStatmentInsert.setString(1, uuid.toString());
            preparedStatmentInsert.setString(2, fulfillmentScheduleEntity.getIdCampaignProgram());
            preparedStatmentInsert.setString(3, fulfillmentScheduleEntity.getTrxKafkaId());
            preparedStatmentInsert.setString(4, fulfillmentScheduleEntity.getMsisdn());
            preparedStatmentInsert.setString(5, Util.formatDateStr(fulfillmentScheduleEntity.getTransactionDate()));
            preparedStatmentInsert.setString(6, Util.formatDateStr(fulfillmentScheduleEntity.getFulfillmentSchedule()));
            preparedStatmentInsert.setString(7, fulfillmentScheduleEntity.getIncentiveCampaignId());
            preparedStatmentInsert.setString(8, fulfillmentScheduleEntity.getEventTopicTrigger());
            preparedStatmentInsert.setString(9, fulfillmentScheduleEntity.getCampaignEvent());
            preparedStatmentInsert.setString(10, fulfillmentScheduleEntity.getMainTrigger());
            preparedStatmentInsert.setString(11, fulfillmentScheduleEntity.getOtherParamTrigger());
            preparedStatmentInsert.setString(12, fulfillmentScheduleEntity.getBonus());
            preparedStatmentInsert.setString(13, Util.formatDateStrUntilMs(fulfillmentScheduleEntity.getCreatedAt()));
            result = executeUpdateOrInsert(preparedStatmentInsert, query);

        } catch (Exception e) {
            Util.debugLogger.debug("Save Campaign Program Error {}, caused by {}", e.getMessage(), e.getCause());
            throw new CampaignProgramException(CampaignProgramErrorMap.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    public int executeUpdateOrInsert(PreparedStatement preparedStatement, String query) throws CampaignProgramException {
        try {
            return preparedStatement.executeUpdate();
        } catch (Exception e){
            Util.debugLogger.debug("Failed Execute Update Or Insert Query {} , {}, caused by {}",query,e.getMessage(),e.getCause());
            Util.debugLogger.fatal("Failed Execute Update Or Insert Query {} , {}, caused by {}",query,e.getMessage(),e.getCause(),e);
            throw new CampaignProgramException(e.getMessage(), CampaignProgramErrorMap.INTERNAL_SERVER_ERROR);
        }
    }
}
