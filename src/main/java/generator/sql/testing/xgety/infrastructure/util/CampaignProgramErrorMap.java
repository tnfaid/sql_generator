package generator.sql.testing.xgety.infrastructure.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CampaignProgramErrorMap {
    CAMPAIGN_PROGRAM_SAVE("OK01","CAMPAIGN_PROGRAM_SAVE", "success create new data campaign program"),
    FULFILLMENT_SCHEDULE_SAVE("OK01","FULFILLMENT_SCHEDULE_SAVE", "success create new data fulfillment schedule"),
    INITIALIZATION("ERR00","INITIALIZATION", "INITIALIZATION"),
    INTERNAL_SERVER_ERROR("ERR00","INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR"),
    CAMPAIGN_PROGRAM_SAVE_FAILED("ERR00","CAMPAIGN_PROGRAM_SAVE_FAILED", "failed to save data to campaign program table because internal system error"),
    FULFILLMENT_SCHEDULE_SAVE_FAILED("ERR00","FULFILLMENT_SCHEDULE_SAVE_FAILED", "failed to save data to fulfillment schedule table because internal system error"),
    CAMPAIGN_PROGRAM_EXIST("ERR01","CAMPAIGN_PROGRAM_EXIST", "campaign program is exist or counter is max"),
    CAMPAIGN_PROGRAM_NOT_FOUND("ERR02","CAMPAIGN_PROGRAM_NOT_FOUND", "campaign program not found for scheduling fulfillment"),
    FULFILLMENT_SCHEDULE_EXIST("ERR03","FULFILLMENT_SCHEDULE_EXIST", "incentive schedule is exist or counter is max"),
    FULFILLMENT_SCHEDULE_NOT_ELIGIBLE("ERR04","FULFILLMENT_SCHEDULE_NOT_ELIGIBLE", "campaign program is not eligible because trx date > end date eligible");

    private String errorCode;
    private String errorStatus;
    private String errorDesc;
}
