package engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class ClientsAnswer {
    int[] clientAnswer = new int[0];

    public ClientsAnswer() {
    }

    public int[] getClientAnswer() {
            return clientAnswer;
        }


    public void setAnswer(int[] clientAnswer) {
        Arrays.sort(clientAnswer);
        this.clientAnswer = clientAnswer;
        }
    }

