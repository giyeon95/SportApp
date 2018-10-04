package com.example.giyeo.testbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Intro_Aer_ExpandableListDataPump {

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();


        String diet_detail_1 = "에어로빅은 심혈관의 내구성을 향상시켜주며 근육의 힘과 신축성을 높여줍니다";
        String diet_detail_2 = "정신적 노화를 방지해주며 심폐 지구력 향상과 골다공증 예방에도 좋습니다.";
        String diet_detail_3 = "유산소 운동과 무산소 운동이 섞여있어 다이어트에 탁월한 효과가 있습니다.";

        List<String> diet = new ArrayList<String>();

        diet.add(diet_detail_1);
        diet.add(diet_detail_2);
        diet.add(diet_detail_3);

        String bodyC_detail_1 = "에어로빅은 신나는 음악에 쉽고 재밌는 동작을 따라하기에 남녀노소 누구나 쉽고 재미있게 운동할수 있습니다.";
        String bodyC_detail_2 = "고령화 사회로 인한 대책 즉 고혈압, 당뇨, 관절염등 및 심질환 등 다양한 질환에 도움이 될수 있으며 신체기능을 강화해줍니다.";

        List<String> bodyCorrection = new ArrayList<String>();

        bodyCorrection.add(bodyC_detail_1);
        bodyCorrection.add(bodyC_detail_2);

        String upgradeP_detail_1 = "에어로빅은 하지 근력, 하지 근지구력, 유연성, 균형성등 다양한 근력강화에 도움이 될수 있습니다.";

        List<String> upgradePower = new ArrayList<String>();

        upgradePower.add(upgradeP_detail_1);






        expandableListDetail.put("건강관리", diet);
        expandableListDetail.put("접근성",bodyCorrection);
        expandableListDetail.put("근력강화",upgradePower);

        return expandableListDetail;
    }

}
