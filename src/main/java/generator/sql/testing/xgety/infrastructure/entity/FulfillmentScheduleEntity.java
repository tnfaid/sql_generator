package generator.sql.testing.xgety.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FulfillmentScheduleEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "id_campaign_program")
    private String idCampaignProgram;

    @Column(name = "trx_kafka_id")
    private String trxKafkaId;

    @Column(name = "msisdn")
    private String msisdn;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "fulfillment_schedule")
    private Date fulfillmentSchedule;

    @Column(name = "incentive_campaign_id")
    private String incentiveCampaignId;

    @Column(name = "event_topic_trigger")
    private String eventTopicTrigger;

    @Column(name = "campaign_event")
    private String campaignEvent;

    @Column(name = "main_trigger")
    private String mainTrigger;

    @Column(name = "other_param_trigger")
    private String otherParamTrigger;

    @Column(name = "bonus")
    private String bonus;

    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
}
