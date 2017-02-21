package com.rbc.basketcost.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class FileReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileReader.class);

	/*
	 * Will read a file from the resources folder in the project
	 */
	public static List<String> getListofLinesFromFile(String fileName) {
		List<String> list = new ArrayList<>();


		try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource(fileName).toURI()))) {

			list = stream.collect(Collectors.toList());

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
				
		return list;
	}
	

	
	
	public static BigDecimal getBigDecimalFromString(String val){
		BigDecimal retVal = BigDecimal.valueOf(Double.parseDouble(val));
		
		return retVal.setScale(2, RoundingMode.DOWN);
		
	}
	

}
