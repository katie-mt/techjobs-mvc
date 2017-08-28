package org.launchcode.controllers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    public SearchController() throws IOException {
    }

    //method displaying the search web page
    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    //value is results as referenced in search.html.  GET request to receive input from user
    @RequestMapping(value = "results", method = RequestMethod.GET)
    //two parameters, one for the input, one for the type of the search
    public String processSearchTool(@RequestParam String searchTerm, @RequestParam String searchType, Model model) {
        //similar functionality to listColumnValues in ListController.java
        //if there is an input search term
        if (searchType.equals("all")) {
            //create an ArrayList made up of the jobs
            ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);
            //adds the title and jobs to the ArrayList
            model.addAttribute("title", "Jobs with " + ListController.columnChoices.get(searchType) + ": " + searchTerm);
            model.addAttribute("jobs", jobs);

            //if the search type is not "All" then
        } else {
            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title", "Jobs with " + ListController.columnChoices.get(searchType) + ": " + searchTerm);
            model.addAttribute("jobs", jobs);
        }
            model.addAttribute("columns", ListController.columnChoices);
            return "search";
    }
}
