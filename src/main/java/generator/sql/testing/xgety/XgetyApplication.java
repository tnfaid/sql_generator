package generator.sql.testing.xgety;

import generator.sql.testing.xgety.application.CampaignProgramController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XgetyApplication implements CommandLineRunner {

	@Autowired
	private CampaignProgramController campaignProgramController;


	public static void main(String[] args) {
		SpringApplication.run(XgetyApplication.class, args);
	}

	public void run(String... args) throws  Exception{
		campaignProgramController.submitCampaignProgram();
	}
}
