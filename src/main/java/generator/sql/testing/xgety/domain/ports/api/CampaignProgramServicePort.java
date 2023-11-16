package generator.sql.testing.xgety.domain.ports.api;


import generator.sql.testing.xgety.infrastructure.exception.CampaignProgramException;
import generator.sql.testing.xgety.infrastructure.util.CampaignProgramErrorMap;

public interface CampaignProgramServicePort {
    CampaignProgramErrorMap submitCampaignProgram() throws CampaignProgramException;
}
