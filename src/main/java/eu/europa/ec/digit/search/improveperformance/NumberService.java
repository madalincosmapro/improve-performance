package eu.europa.ec.digit.search.improveperformance;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NumberService {

    private static final int SAMPLE_SIZE = 100_000;
    private Random random = new Random();

    public Integer findSmallestDuplicate(List<Integer> data) {

        List<Integer> duplicates = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {

            for (int j = i + 1; j < data.size(); j++) {

                if (data.get(i).equals(data.get(j))) {

                    log.info("found duplicate {}", data.get(j));
                    duplicates.add(data.get(j));
                }
            }
        }

        return duplicates.stream().sorted().findFirst().orElse(null);

    }

    public Integer findSmallestDuplicateImproved(List<Integer> data) {
    	Set<Integer> set = new HashSet<Integer>();
    	Set<Integer> sortedDuplicates = new TreeSet<Integer>();
    	
    	for(Integer item : data) {
    		if (!set.add(item)) {
    			sortedDuplicates.add(item);
    		}
    	}
    	
    	return sortedDuplicates.stream().findFirst().orElse(null);

    }

    public List<Integer> generateData() {

        List<Integer> data = IntStream.range(0, SAMPLE_SIZE).boxed().collect(toList());
        
        data.add(data.get(random.nextInt(data.size())));
        log.info("first duplicate number is: {}", data.get(data.size() - 1));
        data.add(data.get(random.nextInt(data.size())));
        log.info("second duplicate number is: {}", data.get(data.size() - 1));
        Collections.shuffle(data);

        return data;
    }
}
