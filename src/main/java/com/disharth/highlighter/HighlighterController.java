package com.disharth.highlighter;

import com.disharth.highlighter.service.EntityGraphService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/model")
public class HighlighterController {

    EntityGraphService entityGraphService = new EntityGraphService();
    @RequestMapping(value = "/relationship" , method= RequestMethod.GET , produces = "application/json;charset=UTF-8")
    public @ResponseBody
    ResponseEntity<?> getAllCategories(@RequestParam(value="entities" , defaultValue = "0" ) String entities ){

         int[] numbersIntArray = Stream.of(entities.split(" ")).mapToInt(Integer::parseInt).toArray();


        return ResponseEntity.ok(ResponseWrapper.wrapAllCategoriesToResponse(entityGraphService.generateRelationshipGraph(numbersIntArray)));

    }


}
