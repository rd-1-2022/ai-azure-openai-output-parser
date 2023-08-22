package org.springframework.samples.ai.azure.openai.output;

import org.springframework.ai.client.AiClient;
import org.springframework.ai.client.Generation;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.ai.prompt.Prompt;
import org.springframework.ai.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OutputParserController {

    private final AiClient aiClient;

    @Autowired
    public OutputParserController(AiClient aiClient) {
        this.aiClient = aiClient;
    }

    @GetMapping("/ai/output")
    public ActorsFilms generate(@RequestParam(value = "actor", defaultValue = "Jeff Bridges") String actor) {
        BeanOutputParser<ActorsFilms> outputParser = new BeanOutputParser<>(ActorsFilms.class);

        String format = outputParser.getFormat();
        String template = """
				Generate the filmography for the actor {actor}.
				{format}
				""";
        PromptTemplate promptTemplate = new PromptTemplate(template, Map.of("actor", actor, "format", format));
        Prompt prompt = new Prompt(promptTemplate.createMessage());
        Generation generation = aiClient.generate(prompt).getGeneration();

        ActorsFilms actorsFilms = outputParser.parse(generation.getText());
        return actorsFilms;
    }
}
