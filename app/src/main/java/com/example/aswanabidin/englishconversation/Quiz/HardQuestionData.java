package com.example.aswanabidin.englishconversation.Quiz;

/**
 * Created by aswanabidin on 11/7/17.
 */

public class HardQuestionData {

    // array untuk pertanyaan
    private String pertanyaan [] = {
            "Kristen is coming to the party tonight.  Is she? I thought you said she _______________",
            "We were the only guests at the hotel. _______________",
            "I’m busy right now, but I’ll be with you ____ a moment",
            "House prices are very high. They’ve _______________ a lot in the last few years.",
            "Which of the four answers is wrong or very unlikely. She thinks _________________ living away from home when he goes to University.",
            "She left the lights on overnight and in the morning couldn't ____________ the car started.",
            " Choose the correct sentence that combines the following two sentences into one!",
            "You can exchange the gift _____ it is returned within seven days.",
            " ‘You should stop smoking' ",
            "I didn't understand at first, but then ____ and the answer was clear."

    };

    // array untuk pilihan jawaban dari setiap pertanyaan
    private String pilihanjawaban [][] = {
            {"Could come?","Was going to come?","Wasn't going to come?","Would be able to come?"},
            {"Nobody else had stayed there","Nobody else was staying there","Nobody else even stayed there","Nobody else stayed there"},
            {"On","At","In","Before"},
            {"Going up","Will have gone up"," Going to go up","Gone up"},
            {"Dan will enjoy","Dan is going to enjoy","Dan is enjoying","Dan would enjoy"},
            {"Have","Had","Get","Got"},
            {"The bus, that goes to the airport, arrives every half hour","The bus arrives every half hour, which goes to the airport",
                    "The bus which goes to the airport arrives every half hour","The bus goes to the airport which arrives every half hour"},
            {"So long that","While","As long as","Meanwhile"},
            {"He demanded her to stop smoking","He advised her to stop smoking","He threatened her to stop smoking","He said her to stop smoking"},
            {"He explained me","He explained it me","He explained it to me","He explained me it"}
    };

    // array untuk jawaban yang benar
    private String jawabanbenar[] = {"Wasn't going to come?","Nobody else was staying there","In","Going up",
            "Dan is going to enjoy","Get","The bus goes to the airport which arrives every half hour",
            "As long as","He advised her to stop smoking","He explained it to me"};



    // method untuk mengembalikan nomor pertanyaan
    public int getLength() {
        return pertanyaan.length;
    }

    // method untuk mengembalikan pertanyaan dari array pertanyaan[] dalam array index
    public String getPertanyaan(int a) {
        String question = pertanyaan[a];
        return  question;
    }

    // method return a single multiple single chocie item for question based on array index,
    // based on number of multiple choice item in the list - 1, 2, 3, or 4 as an argument
    public String getPilihanJawaban(int index, int num) {
        String pilJawaban = pilihanjawaban[index][num-1];
        return pilJawaban;
    }

    // method untuk mengembalikan jawaban benar dari pertanyaan dalam array index
    public String getJawabanBenar(int a){
        String answer = jawabanbenar[a];
        return answer;
    }
}
