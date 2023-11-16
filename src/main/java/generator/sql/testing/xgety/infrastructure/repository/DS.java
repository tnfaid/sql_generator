package generator.sql.testing.xgety.infrastructure.repository;


import generator.sql.testing.xgety.infrastructure.exception.CampaignProgramException;
import generator.sql.testing.xgety.infrastructure.util.CampaignProgramErrorMap;
import generator.sql.testing.xgety.infrastructure.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DS {

    public void closeDBConnection(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection){
        try {
            if(resultSet != null) resultSet.close();
            if(preparedStatement != null) preparedStatement.close();
            if(connection != null) connection.close();
        } catch (Exception e){
            String msg = "Failed To Close Connection " + e.getMessage();
            Util.debugLogger.error(msg,e);
        }
    }

    public void closeDBConnection(ResultSet resultSet){
        try {
            if(resultSet != null) resultSet.close();
        } catch (Exception e){
            String msg = "Failed To Close Connection " + e.getMessage();
            Util.debugLogger.error(msg,e);
        }
    }

    public ResultSet executeGet(PreparedStatement preparedStatement, String query) throws CampaignProgramException {
        try {
            return preparedStatement.executeQuery();
        } catch (Exception e){
            Util.debugLogger.debug("Failed Execute Query Get {} , {}, caused by {}",query,e.getMessage(),e.getCause());
            Util.debugLogger.fatal("Failed Execute Query Get {} , {}, caused by {}",query,e.getMessage(),e.getCause(),e);
            throw new CampaignProgramException(e.getMessage(), CampaignProgramErrorMap.INTERNAL_SERVER_ERROR);
        }
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
