package searchmethods;

import agent.Problem;
import agent.Solution;

public interface SearchMethod {

    Solution Search(Problem problem);

    Statistics GetStatistics();

    void Stop();

    boolean HasBeenStopped();
}
