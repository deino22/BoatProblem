package agent;

import searchmethods.BreadthFirstSearch;
import searchmethods.SearchMethod;
import searchmethods.UniformCostSearch;

import java.util.ArrayList;

public class Agent<E extends State> {

    protected E environment;
    protected ArrayList<SearchMethod> searchMethods;
    protected SearchMethod searchMethod;
    protected ArrayList<Heuristic> heuristics;
    protected Heuristic heuristic;
    protected Solution solution;

    public Agent(E environment) {
        this.environment = environment;
        searchMethods = new ArrayList<>();

        searchMethods.add(new UniformCostSearch());
        searchMethods.add(new BreadthFirstSearch());

        searchMethod = searchMethods.get(0);
        heuristics = new ArrayList<>();
    }

    public Solution SolveProblem(Problem problem) {
        if (heuristic != null) {
            problem.SetHeuristic(heuristic);
            heuristic.SetProblem(problem);
        }
        solution = searchMethod.Search(problem);
        return solution;
    }

    public void ExecuteSolution() {
        for(Action action : solution.GetActions()){
            environment.ExecuteAction(action);
        }
    }

    public boolean HasSolution() {
        return solution != null;
    }

    public void Stop() {
        getSearchMethod().Stop();
    }

    public boolean HasBeenStopped() {
        return getSearchMethod().HasBeenStopped();
    }

    public E GetEnvironment() {
        return environment;
    }

    public void SetEnvironment(E environment) {
        this.environment = environment;
    }

    public SearchMethod[] GetSearchMethodsArray() {
        SearchMethod[] sm = new SearchMethod[searchMethods.size()];
        return searchMethods.toArray(sm);
    }

    public SearchMethod getSearchMethod() {
        return searchMethod;
    }

    public void SetSearchMethod(SearchMethod searchMethod) {
        this.searchMethod = searchMethod;
    }

    public Heuristic[] GetHeuristicsArray() {
        Heuristic[] sm = new Heuristic[heuristics.size()];
        return heuristics.toArray(sm);
    }

    public Heuristic GetHeuristic() {
        return heuristic;
    }

    public void SetHeuristic(Heuristic heuristic) {
        this.heuristic = heuristic;
    }

    public String GetSearchReport() {
        StringBuilder sb = new StringBuilder();
        sb.append(searchMethod + "\n");
        if (solution == null) {
            sb.append("No solution found\n");
        } else {
            sb.append("Solution cost: " + Double.toString(solution.GetCost()) + "\n");
        }
        sb.append("Num of expanded nodes: " + searchMethod.GetStatistics().numExpandedNodes + "\n");
        sb.append("Max frontier size: " + searchMethod.GetStatistics().maxFrontierSize + "\n");
        sb.append("Num of generated nodes: " + searchMethod.GetStatistics().numGeneratedNodes+ "\n");

        return sb.toString();
    }
}
