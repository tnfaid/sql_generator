package generator.sql.testing.xgety.application;

import generator.sql.testing.xgety.domain.ports.api.CampaignProgramServicePort;
import generator.sql.testing.xgety.infrastructure.exception.CampaignProgramException;
import generator.sql.testing.xgety.infrastructure.util.CampaignProgramErrorMap;
import generator.sql.testing.xgety.infrastructure.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;

@Controller
public class CampaignProgramController {

    @Autowired
    private CampaignProgramServicePort campaignProgramServicePort;

    public String submitCampaignProgram() throws CampaignProgramException {
        CampaignProgramErrorMap result = CampaignProgramErrorMap.INITIALIZATION;

        campaignProgramServicePort.submitCampaignProgram();

        return result.getErrorCode() + "-" + result.getErrorDesc();
    }

}
