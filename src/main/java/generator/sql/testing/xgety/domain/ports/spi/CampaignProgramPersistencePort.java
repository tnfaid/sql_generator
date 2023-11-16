package generator.sql.testing.xgety.domain.ports.spi;


import generator.sql.testing.xgety.domain.data.FulfillmentScheduleDTO;
import generator.sql.testing.xgety.infrastructure.exception.CampaignProgramException;

public interface CampaignProgramPersistencePort {
    Integer insertFulfillmentSchedule(FulfillmentScheduleDTO fulfillmentScheduleDTO) throws CampaignProgramException;
}
