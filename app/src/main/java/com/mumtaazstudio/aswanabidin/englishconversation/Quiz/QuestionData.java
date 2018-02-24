package com.mumtaazstudio.aswanabidin.englishconversation.Quiz;

/**
 * Created by aswanabidin on 11/6/17.
 */

public class QuestionData {

    // array untuk pertanyaan
    private String pertanyaan [] = {
            "Faiz and Rizqi is in the cinema to watch a movie. Suddenly they meets Aji, friend of Faiz" +
                    "outside of the cinema. \nFaiz : Hello Aji, Good Afternoon. \nAji : Good Afternoon, Faiz _______",
            "Faiz : I'm fine thank you and how about you? \nAji : _______",
            "Faiz : Oh, this is my friend, Rizki. \nAji : Hello Rizki, my name is Aji. _______",
            "Rizki : Hi, my name is Rizki. Nice to meet you too. \nFaiz : Well Aji, I think we have to go now," +
                    "the movie is started to play. \nAji : Oh okay. _______ \nFaiz : See you.",
            "Ciko : _______, where are you going? \nVita : Good afternoon, I'll have lunch at the canteen." +
                    "\nCiko : Let's go together.",
            "Ester : I'm Ester. How do you do? \nFitri : _______ I'm Fitriyani Maida.",
            "Bobby : Hi, Ace. _______ \nAce : Great! How about you? \nBobby : It's good, thanks.",
            "The way to ask someone's news is?",
            "It's seven o'clock in the morning. Gina will go to school. She says _______ to his parents.",
            "It's nine o'clock in the night. Faiz will go to sleep. He says _______ to his parents."
    };

    // array untuk pilihan jawaban dari setiap pertanyaan
    private String pilihanjawaban [][] = {
            {"Nice to meet you.","Where are you?","How are you?","Glad to see you.","How is your mother?"},
            {"I'm fine too.","Nice to meet you.","Good to see you.","See you later.","Good afternoon."},
            {"How are you?","Good afternoon.","Nice to meet you.","Glad to see you.","See you later."},
            {"Where are you going?","See you later.","How are you?","Nice to meet you.","How are you?"},
            {"Good night","Good morning","Good afternoon","Good bye","Nice to meet you"},
            {"Are you ok?","How are you?","How do you do","I am fine","Good morning"},
            {"What are you doing?","Where have you been?","What is she doing?","Glad to see you.","How's life?"},
            {"How is life?","Where are you?","What is that?","Do you like it?","When is your birthday?"},
            {"Good night","Good afternoon","Good bye","Good morning","Good day"},
            {"Good night","Good afternoon","Good bye","Good morning","Good day"}
    };

    // array untuk jawaban yang benar
    private String jawabanbenar[] = {"How are you?","I'm fine too.","Nice to meet you.","See you later.",
    "Good afternoon","How do you do","How's life?","How is life?","Good morning","Good night"};



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



