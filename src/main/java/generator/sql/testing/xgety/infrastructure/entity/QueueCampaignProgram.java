package generator.sql.testing.xgety.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import generator.sql.testing.xgety.infrastructure.util.Util;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.ThreadContext;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QueueCampaignProgram {

    @JsonProperty("trx_kafka_id")
    @SerializedName("trx_kafka_id")
    private String trxKafkaId;

    @JsonProperty("trx_kafka_date")
    @SerializedName("trx_kafka_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String trxKafkaDate;

    @JsonProperty("msisdn")
    @SerializedName("msisdn")
    private String msisdn;

    @JsonProperty("main_trigger")
    @SerializedName("main_trigger")
    private JsonObject mainTrigger;

    @JsonProperty("other_param_trigger")
    @SerializedName("other_param_trigger")
    private JsonObject otherParamTrigger;

    @JsonProperty("bonus")
    @SerializedName("bonus")
    private JsonObject bonus;

    @JsonProperty("incentive_campaign_id")
    @SerializedName("incentive_campaign_id")
    private String incentiveCampaignId;

    @JsonProperty("campaign_event")
    @SerializedName("campaign_event")
    private String campaignEvent;

    @JsonProperty("fulfillment_date")
    @SerializedName("fulfillment_date")
    private String fulfillmentDate;

    @JsonProperty("event_topic_trigger")
    @SerializedName("event_topic_trigger")
    private String eventTopicTrigger;

    @JsonProperty("counter")
    @SerializedName("counter")
    private Integer counter;

    @JsonProperty("campaign_program_trigger")
    @SerializedName("campaign_program_trigger")
    private JsonObject campaignProgramTrigger;

    public QueueCampaignProgram(String message) {
        long startTime = System.currentTimeMillis();
        Util.debugLogger.debug("Message Masuk {}, response time{}", message, System.currentTimeMillis() - startTime);
        JsonObject messageJsonObject = new Gson().fromJson(message, JsonObject.class);

        this.trxKafkaId = messageJsonObject.get("trx_kafka_id").getAsString();
        this.trxKafkaDate = messageJsonObject.get("trx_kafka_date").getAsString();
        this.msisdn = messageJsonObject.get("msisdn").getAsString();
        ThreadContext.put("msisdn", this.msisdn);
        this.mainTrigger = messageJsonObject.get("main_trigger").getAsJsonObject();
        this.otherParamTrigger = messageJsonObject.get("other_param_trigger").getAsJsonObject();
        this.bonus = messageJsonObject.get("bonus").getAsJsonObject();
        this.incentiveCampaignId = messageJsonObject.get("incentive_campaign_id").getAsString();
        this.campaignEvent = messageJsonObject.get("campaign_event").getAsString();
        this.fulfillmentDate = messageJsonObject.get("fulfillment_date").getAsString();
        this.eventTopicTrigger = messageJsonObject.get("event_topic_trigger").getAsString();
        this.counter = messageJsonObject.get("counter").getAsInt();
        this.campaignProgramTrigger = messageJsonObject.get("campaign_program_trigger").getAsJsonObject();
    }
}
