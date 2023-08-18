package prgrms.lev1;

import java.util.*;

/**
 * @date    2023-08-04
 * @title   신고 결과 받기(92334), L1
 * @tags    Hash(해시)
 * @input
 * 이용자의 ID가 담긴 문자열 배열 id_list,
 * 각 이용자가 신고한 이용자의 ID 정보가 담긴 문자열 배열 report,
 * 정지 기준이 되는 신고 횟수 k가 매개변수로 주어집니다.
 * @output
 * 각 유저별로 처리 결과 메일을 받은 횟수를 배열에 담아 return 하도록 solution 함수를 완성해주세요.
 */
public class PRGRMS_92334 {

    /**
     * <pre>
     *     이렇게 데이터를 계속해서 원하는 형태로 가공해야 하는 문제는
     *     문제를 해결하는 것은 할 수 있으나, 불필요한 가공인지 아닌지 계속 생각하게 된다.
     *
     *     문제 요구사항
     *      - 유저를 여러번 신고할 수 있으나, 각 유저가 동일한 유저를 신고하는 경우 1회만 신고 처리된다.
     *      - 정답 데이터의 순서는 id_list의 유저 순서와 동일하다.
     *
     *     신고 당하는 사람을 key, 신고 한 사람 목록을 value로 HashMap 생성하는 방법
     * </pre>
     * */
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        Map<String, Integer> nameMap = new HashMap<>();
        Map<String, List<String>> reportMap = new HashMap<>();

        // nameMap 초기화, reportMap ArrayList 초기화
        for (int i = 0; i < id_list.length; i++) {
            nameMap.put(id_list[i], i);
            reportMap.put(id_list[i], new ArrayList<>());
        }

        // 동일한 신고자가 없는 경우에만 피신고자(key)에 신고자 저장
        for (String rep : report) {
            String reporter = rep.split(" ")[0];
            String reported = rep.split(" ")[1];
            if (!reportMap.get(reported).contains(reporter)) {
                reportMap.get(reported).add(reporter);
            }
        }

        // 피신고자를 신고한 사람이 k보다 많은 경우
        // nameMap에 저장한 순서값을 idx로 answer 값 ++;
        for (String reported : reportMap.keySet()) {
            if (reportMap.get(reported).size() >= k) {
                for (String reporter : reportMap.get(reported)) {
                    answer[nameMap.get(reporter)]++;
                }
            }
        }

        return answer;
    }
}
