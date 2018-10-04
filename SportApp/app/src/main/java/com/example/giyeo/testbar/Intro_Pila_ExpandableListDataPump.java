package com.example.giyeo.testbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Intro_Pila_ExpandableListDataPump {



    public static HashMap<String, List<String>> getData() {
            HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();


            String diet_detail_1 = "다이어트와 동시에 탄력적이고 예쁜 라인의 몸을 만들어 준다.";
            String diet_detail_2 = "가늘고 긴 근육을 만들어주기 때문에 여성의 예쁜 라인을 만들어 주는데 효과적입니다.";

            List<String> diet = new ArrayList<String>();

            diet.add(diet_detail_1);
            diet.add(diet_detail_2);

            String bodyC_detail_1 = "골격을 지탱해주는 근육을 단련시켜줌으로써 좋은 자세를 만들고 유지시켜주는데 효과적입니다.";
            String bodyC_detail_2 = "자세로 인해 오는 각종 문제들, 혈액순환, 장애, 가슴의 답답함, 허리나 목의 통증을 예방하고 치료해주는 효과가 있습니다.";

            List<String> bodyCorrection = new ArrayList<String>();

            bodyCorrection.add(bodyC_detail_1);
            bodyCorrection.add(bodyC_detail_2);

            String upgradeP_detail_1 = "필라테스는 관절의 가동 범위를 넓게하고 근육을 부드럽게 이완시켜 관절을 튼튼하고 유연하게합니다.";
            String upgradeP_detail_2 = "정적인 운동와 동적인 운동이 결합되었기 때문에 모든 근육을 단력시켜줍니다.";
            String upgradeP_detail_3 = "특정한 운동에서만 효과를 나타내는것이 아니라 일상생활에서 도움을 주는 기능적이고 효율적인 근력과 지구력을 길러줍니다.";

            List<String> upgradePower = new ArrayList<String>();

            upgradePower.add(upgradeP_detail_1);
            upgradePower.add(upgradeP_detail_2);
            upgradePower.add(upgradeP_detail_3);

            String rehabilitation_detail_1 = "약해진 관절을 감싸고 있는 근육들을 강하게 만들어주는 효과로 인해 신체의 각종 부분들을 강하게 만들어줍니다.";
            String rehabilitation_detail_2 = "오랜시간 같은 동작을 하는 운동선수들이나 컴퓨터나 휴대폰이 없이 살수 없는 현대인들은 허리나 척추, 골반 등에 무리가 생기고 피로가 쌓인것을 풀어주는 역활을 합니다.";

            List<String> rehabilitation = new ArrayList<String>();

            rehabilitation.add(rehabilitation_detail_1);
            rehabilitation.add(rehabilitation_detail_2);

            String mindCon_detail_1 = "과중한 업무와 시간에 쫒기는 생활로 인해 스트레스를 호흡조절을 통해 심신을 편안하고 안정되게 합니다.";
            String mindCon_detail_2 = "집중력 향상과 스트레스 해소에 효과적이며, 우리가 평소 잘 쓰지 않는 말초신경의 근육들까지 사용하기 때문에 자신의 신체를 인지하고 조절하는 법을 배우게 됩니다.";


            List<String> mindControl = new ArrayList<String>();
            mindControl.add(mindCon_detail_1);
            mindControl.add(mindCon_detail_2);





            expandableListDetail.put("다이어트", diet);
            expandableListDetail.put("체형교정",bodyCorrection);
            expandableListDetail.put("심신의 안정",mindControl);
            expandableListDetail.put("재활치료",rehabilitation);
            expandableListDetail.put("유연성 및 근력 강화",upgradePower);

            return expandableListDetail;
    }
}
