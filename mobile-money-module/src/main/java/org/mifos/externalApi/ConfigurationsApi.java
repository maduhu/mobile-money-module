package org.mifos.externalApi;

/**
 * Created by daniel on 11/5/16.
 */
import org.hibernate.jpa.criteria.predicate.IsEmptyPredicate;
import org.mifos.exceptions.InvalidParameterException;
import org.mifos.repos.ConfigurationsRepository;
import org.mifos.portfolio.Configurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.metadata.ElementDescriptor;

@RestController
public class ConfigurationsApi {

    @SuppressWarnings("unused")
    @CrossOrigin
    @RequestMapping(value="/mobile_mo/api/v1/configurations", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity<String> initialConfigurations(
            @RequestParam(value="region", required=false)String region,
            @RequestParam(value="country", required=false) String country,
            @RequestParam(value="apiName", required=true)String apiName,
            @RequestParam(value="org_phone", required=false)int phone,
            @RequestParam(value="orgAccId", required=true)int orgAccId,
            @RequestParam(value="urls", required=true)String urls,
            @RequestParam(value="params", required=true)String params){

        Configurations config = new Configurations();

        config.setRegion(region);
        config.setCountry(country);
        config.setApiName(apiName);
        config.setPhone(phone);
        config.setOrgAccId(orgAccId);
        config.setUrls(urls);
        config.setParameters(params);

        // save configurations to the database
        try{
            configuartionsRepo.save(config);
        } catch(Exception ex){
            System.out.println("Error saving configurations: " + ex.getMessage());
            return new ResponseEntity<String>("\"Failure in setting configurations\"", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("\"Configurations successfully set\"", HttpStatus.OK);
    }

    @SuppressWarnings("unused")
    @CrossOrigin
    @RequestMapping(value="/mobile_mo/api/v1/configurations", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity<Iterable<Configurations>> getDefaultConfigurations(
            @RequestParam(value="command", required=true)String command
    ){
        if(String.valueOf(command) == null){
            throw new InvalidParameterException();
        }

        if (command.equals("getDefaultConfiguration")) {

            Iterable<Configurations> config;

            // Get default transactions from the database
            config = configuartionsRepo.findByDefaultId(1);

            return new ResponseEntity<Iterable<Configurations>>(config, HttpStatus.OK);
        } else if(command.equals("getAllConfigurations")) {

            Iterable<Configurations> configs;

            configs = configuartionsRepo.findAll();
            return new ResponseEntity<Iterable<Configurations>>(configs, HttpStatus.OK);
        } else {
            throw new InvalidParameterException();
        }
    }

    @CrossOrigin
    @RequestMapping(value="/mobile_mo/api/v1/configurations", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity<String> deleteConfigurations(
            @RequestParam(value="command", required=true)String command,
            @RequestParam(value="id", required = false)Long configId
    ){
        if(String.valueOf(command) == null || String.valueOf(configId) == null){
            throw new InvalidParameterException();
        }

        if(command.equals("delete")){
            if (configId.equals("")) {

                Iterable<Configurations> config;

                //delete all configurations from the database
                configuartionsRepo.deleteAll();

                return new ResponseEntity<String>("\"Configurations successfully deleted.\"", HttpStatus.OK);
            } else {

                // delete configuration of matching id
                configuartionsRepo.delete(configId);
                return new ResponseEntity<String>("\"Configurations successfully set\"", HttpStatus.OK);
            }
        }
        return null;
    }


    @Autowired
    private ConfigurationsRepository configuartionsRepo;

}
