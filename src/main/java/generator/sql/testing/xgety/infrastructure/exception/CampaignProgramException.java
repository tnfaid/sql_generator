package generator.sql.testing.xgety.infrastructure.exception;


import generator.sql.testing.xgety.infrastructure.util.CampaignProgramErrorMap;

public class CampaignProgramException extends Exception {
    private final CampaignProgramErrorMap campaignProgramErrorMap;

    public CampaignProgramException(CampaignProgramErrorMap campaignProgramErrorMap) {
        super(campaignProgramErrorMap.name());
        this.campaignProgramErrorMap = campaignProgramErrorMap;
    }

    public CampaignProgramException(String message, CampaignProgramErrorMap campaignProgramErrorMap) {
        super(message);
        this.campaignProgramErrorMap = campaignProgramErrorMap;
    }

    public CampaignProgramErrorMap getCampaignProgramErrorMap() {
        return campaignProgramErrorMap;
    }
}
