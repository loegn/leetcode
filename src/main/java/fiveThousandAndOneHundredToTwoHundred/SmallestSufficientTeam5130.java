package fiveThousandAndOneHundredToTwoHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date : 2019/07/15 17:28:38
 * @author: liangenmao
 */
public class SmallestSufficientTeam5130 {
    public int[] result1(String[] req_skills, List<List<String>> people) {
        int[] skillNum = new int[req_skills.length];
        Map<String, ArrayList<Integer>> skillPeople = new HashMap<>();
        for (String skill : req_skills) {
            skillPeople.put(skill, new ArrayList<>());
        }
        for (int i = 0; i < people.size(); i++) {
            List<String> list = people.get(i);
            final int num = i;
            list.forEach(s -> skillPeople.get(s).add(num));
        }
        List<Integer> resultList = new ArrayList<>();
        helper(skillNum,skillPeople,resultList,people,req_skills);
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }

    private void helper(int[] skillNum, Map<String, ArrayList<Integer>> skillPeople, List<Integer> resultList, List<List<String>> people, String[] req_skills) {
        for (int i = 0; i < skillNum.length; i++) {
            if (skillNum[i] <= 0){
                for (Integer num : skillPeople.get(req_skills[i])) {
                    for (String skill : people.get(num)) {

                    }
                }
            }
        }
    }

    @Test
    public void smallestSufficientTeam() {
        String[] req_skills = null;
        List<List<String>> people = null;
        Object result = result1(req_skills, people);
        PrintUtils.print(result);
    }
}
