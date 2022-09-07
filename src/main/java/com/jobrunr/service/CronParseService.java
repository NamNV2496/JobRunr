package com.jobrunr.service;

import com.cronutils.model.Cron;
import static com.cronutils.model.CronType.QUARTZ;
import com.cronutils.descriptor.CronDescriptor;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.parser.CronParser;

import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class CronParseService {

    public void parseCron() {
//    way 1
        //get a predefined instance
        CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(QUARTZ);

        //create a parser based on provided definition
        CronParser parser = new CronParser(cronDefinition);
        Cron quartzCron = parser.parse("0 * * 1-3 * ? *");

        //create a descriptor for a specific Locale
        CronDescriptor descriptor = CronDescriptor.instance(Locale.UK);

        //parse some expression and ask descriptor for description
        String description = descriptor.describe(parser.parse("*/45 * * * * ?"));
        //description will be: "every 45 seconds"

        //validate expression
        quartzCron.validate();


//    way 2
        CronExpression expression = CronExpression.parse("10 * * * * *");
//    LocalDateTime result = expression.next(LocalDateTime.now());
        System.out.println(expression);
    }
}
