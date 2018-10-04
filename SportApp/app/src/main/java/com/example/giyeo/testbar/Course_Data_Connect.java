package com.example.giyeo.testbar;

public class Course_Data_Connect {

    private Course_Data_Connect() {

    }

    public static Course_Data_Connect getInstance() {
        return LazyHolder.INSTANCE;
    }
    public Course_Data_Repository courseAerBeginner1 =
            new Course_Data_Repository(
                "업 코브라",
                "■ 엎드린 자세에서 양손을 어깨 옆에 놓는다.\n\n" +
                    "■ 숨을 내쉬면서 팔을 피고 상체를 세운다. 들이 마시면서 제자리로 온다.\n\n" +
                    "■ 요추 근육에 집중한다. 자신에 맞게 동작을 한다.\n",
                "course progress (1/9)",
                "● 작용부위 : 척추기립근, 다열근"
            );

    public Course_Data_Repository courseAerBeginner2 =
            new Course_Data_Repository(
                    "종아리 풀기",
                    "■ 아크바렐을 종아리를 올려놓는다.\n\n" +
                            "■ 다리를 좌,우로 흔들어 주어 종아리 근육을 풀어준다.\n\n" +
                            "■ 굳어 있는 근육을 집중적으로 풀어 준다.\n",
                    "course progress (2/9)",
                    "● 작용부위 : 종아리 근육 이완, 다리 슬리밍"
            );

    private static class LazyHolder {
        public static final Course_Data_Connect INSTANCE = new Course_Data_Connect();
    }
}
