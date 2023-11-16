package generator.sql.testing.xgety.domain.service;

import com.google.gson.Gson;
import generator.sql.testing.xgety.domain.data.FulfillmentScheduleDTO;
import generator.sql.testing.xgety.domain.ports.api.CampaignProgramServicePort;
import generator.sql.testing.xgety.domain.ports.api.*;
import generator.sql.testing.xgety.domain.ports.spi.CampaignProgramPersistencePort;
import generator.sql.testing.xgety.infrastructure.configuration.ConstantConfig;
import generator.sql.testing.xgety.infrastructure.entity.QueueCampaignProgram;
import generator.sql.testing.xgety.infrastructure.exception.CampaignProgramException;
import generator.sql.testing.xgety.infrastructure.util.CampaignProgramErrorMap;
import generator.sql.testing.xgety.infrastructure.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
@AllArgsConstructor
public class SubmitCampaignProgramServiceImpl implements CampaignProgramServicePort {

    @Autowired
    private CampaignProgramPersistencePort campaignProgramPersistencePort;

    @Override
    public CampaignProgramErrorMap submitCampaignProgram() throws CampaignProgramException {
        long startTime = System.currentTimeMillis();
        CampaignProgramErrorMap result = null;

        try {
            result = checkMethodCampaignProgram();
            return result;
        } catch (CampaignProgramException e) {
            throw e;
        } catch (Exception e) {
            Throwable exception = Util.lastThrowable(e);
            Util.debugLogger.error("Submit Campaign Program failed|{}|{}, response time{}", exception.getMessage(), exception.getCause(), System.currentTimeMillis() - startTime);
            throw new CampaignProgramException(exception.getMessage(), CampaignProgramErrorMap.INITIALIZATION);
        }
    }

    public CampaignProgramErrorMap checkMethodCampaignProgram() throws CampaignProgramException {
        CampaignProgramErrorMap result = null;
        FulfillmentScheduleDTO fulfillmentScheduleDTO = new FulfillmentScheduleDTO();
        Gson g = new Gson();
        QueueCampaignProgram queueCampaignProgram1 = g.fromJson(ConstantConfig.getInstance().getQueueCheck(), QueueCampaignProgram.class);

        LocalDate startDate = Util.stringToLocalDate(queueCampaignProgram1.getTrxKafkaDate());
        String fulfillmentSchedule = Util.dateEveryMonth(startDate, Integer.parseInt(queueCampaignProgram1.getFulfillmentDate()));

        for (int i=0; i <= ConstantConfig.getInstance().getCountBlastInject(); i++){
            System.out.println("data ke "+i);
            result = InsertFulfillmentSchedule(fulfillmentScheduleDTO, startDate, fulfillmentSchedule, queueCampaignProgram1, i);
        }
        return result;
    }

    public CampaignProgramErrorMap InsertFulfillmentSchedule(FulfillmentScheduleDTO fulfillmentScheduleDTO,
                                                             LocalDate startDate, String fulfillmentSchedule,
                                                             QueueCampaignProgram queueCampaignProgram1, int i) throws CampaignProgramException {
        CampaignProgramErrorMap result = null;

        // Insert Fulfillment Schedule
        fulfillmentScheduleDTO.setIdCampaignProgram(incrementNumber("CP", ConstantConfig.getInstance().getIdTableCP(), i));
        fulfillmentScheduleDTO.setTrxKafkaId(incrementNumber("IdKafka-", ConstantConfig.getInstance().getTrxKafkaTableCP(), i));
        fulfillmentScheduleDTO.setMsisdn(incrementNumber("",ConstantConfig.getInstance().getMsisdnTableCP(), i));
        fulfillmentScheduleDTO.setTransactionDate(java.sql.Date.valueOf(startDate));
        fulfillmentScheduleDTO.setFulfillmentSchedule(java.sql.Date.valueOf(fulfillmentSchedule));
        fulfillmentScheduleDTO.setIncentiveCampaignId(queueCampaignProgram1.getIncentiveCampaignId());
        fulfillmentScheduleDTO.setEventTopicTrigger(queueCampaignProgram1.getEventTopicTrigger());
        fulfillmentScheduleDTO.setCampaignEvent(queueCampaignProgram1.getCampaignEvent());
        fulfillmentScheduleDTO.setMainTrigger(incrementNumber("business_id|",ConstantConfig.getInstance().getMainTriggerTableCP(),i));
        fulfillmentScheduleDTO.setOtherParamTrigger(queueCampaignProgram1.getOtherParamTrigger().getAsJsonObject().toString());
        fulfillmentScheduleDTO.setBonus(queueCampaignProgram1.getBonus().getAsJsonObject().toString());
        fulfillmentScheduleDTO.setCreatedAt(new Date());
        campaignProgramPersistencePort.insertFulfillmentSchedule(fulfillmentScheduleDTO);
        result = CampaignProgramErrorMap.FULFILLMENT_SCHEDULE_SAVE;

        return result;
    }

    public static String incrementNumber(String name, String number, int i) {
        try {
            // Convert the number to an integer
            Long numberInt = Long.parseLong(number+i);
            System.out.println("valid format for " + name + numberInt);

            // Convert it back to a string
            return Long.toString(numberInt);
        } catch (NumberFormatException e) {
            // Handle the case where number is not a valid integer
            System.out.println("\n\n\n----------------------------------------" +
                    "\nInvalid number format: " + number);
            return null;
        }
    }

}
