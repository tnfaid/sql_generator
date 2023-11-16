package generator.sql.testing.xgety.domain.data;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FulfillmentScheduleDTO {

    @SerializedName("id")
    private String id;

    @SerializedName("id_campaign_program")
    private String idCampaignProgram;

    @SerializedName("trx_kafka_id")
    private String trxKafkaId;

    @SerializedName("msisdn")
    private String msisdn;

    @SerializedName("transaction_date")
    private Date transactionDate;

    @SerializedName("fulfillment_schedule")
    private Date fulfillmentSchedule;

    @SerializedName("incentive_campaign_id")
    private String incentiveCampaignId;

    @SerializedName("event_topic_trigger")
    private String eventTopicTrigger;

    @SerializedName("campaign_event")
    private String campaignEvent;

    @SerializedName("main_trigger")
    private String mainTrigger;

    @SerializedName("other_param_trigger")
    private String otherParamTrigger;

    @SerializedName("bonus")
    private String bonus;

    @SerializedName("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
}
