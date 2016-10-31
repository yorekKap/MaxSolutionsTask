package task.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.protobuf.InvalidProtocolBufferException;

import task.prot.DeveloperProto;
import task.prot.DeveloperProto.Developer;
import task.prot.SoftwareProto.Software;

@Controller
public class MainController {

	@RequestMapping(path = "proccess-software", method = RequestMethod.GET)
	@ResponseBody
	public String getTest(){
		
		Developer dev = DeveloperProto.Developer.newBuilder()
							.setId(1)
							.setName("TestDev")
							.addKnownLanguages(DeveloperProto.ProgrammingLanguage.JAVA)
							.setPhone("+380673255723")
							.build();
		String res = new String(dev.toByteArray());
		
		return res;
	}
	
	@RequestMapping(path = "software-proccess", method = RequestMethod.POST)
	@ResponseBody
	public String getDeveloper(@RequestParam(name = "software") String software){
		
		System.out.println("here");
		String res = null;
		
			try {
				Software soft = Software.parseFrom(software.getBytes());
				
				Developer dev = DeveloperProto.Developer.newBuilder()
						.setId(1)
						.setName("TestDev")
						.addKnownLanguages(soft.getLanguage())
						.setPhone("+380673255723")
						.build();
					
				res = new String(dev.toByteArray());
				
			} catch (InvalidProtocolBufferException e) {
				
				e.printStackTrace();
			}
			
			
		return res;
		
	}
	
}
