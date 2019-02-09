package Solver;

import java.util.HashSet;
import java.util.Set;

public class DFSSolver2 extends PuzzleSolver{
	 /** A new hash set for storing puzzle states. Helps eliminate duplicate states. */
	  protected Set<PuzzleState> stateSet = new HashSet<PuzzleState>();
	  
	  /** The maximum depth of the dfs. */
	  public static final int MAX_DEPTH = 100;
	  
	  /** An instance of the depth-first solver for solving the puzzle. */
	  private static DFSSolver2 instance = new DFSSolver2();
	  
	  /** Nullary constructor. */
	  private DFSSolver2(){
	    
	  }
	  
	  /**
	   * Returns the instance of the depth-first solver.
	   * 
	   * @return the instance of the depth-first solver.
	   */
	  public static DFSSolver2 getInstance(){
	    return instance;
	  }
	  
	  /**
	   * Depth-first search algorithm for recursively solving the puzzle.
	   * 
	   * @param state - the state to explore
	   * @param depth - the depth of the exploration in the dfs tree
	   */
	  void dfs(PuzzleState state,int depth){
	    
	    /* When depth is negative, end recursion. */
	    if(depth < 0)
	      return;
	    
	    /* When input state is the goal state, set it goal to input state. */
	    if(state.isGoalState()){
	      goal = state;
	    }
	    
	    /* When the goal state is saved, end recursion, goal has been found. */
	    if(goal != null)
	      return;
	    
	    /* A new puzzle state for depth-first search. */
	    PuzzleState newState;
	    
	    /* Try to move zero (blank space) up in the puzzle and set the new state if moves. */
	    newState = PuzzleState.moveUp(state);
	    
	    /* Checks if new state exists and is not in state set. */
	    if(newState != null && !stateSet.contains(newState)){
	      
	      /* Add the new state to the state set. */
	      stateSet.add(newState);
	      
	      /* Call recursive dfs method to explore next level of states in tree. */
	      dfs(newState, depth - 1);
	      
	      /* When goal exists, leave recursive search. */
	      if(goal != null)
	        return;
	      
	      /* Remove the new state from the set to save memory. */
	      stateSet.remove(newState);
	    }
	    
	    /* Try to move zero (blank space) down in the puzzle and set the new state if moves. */
	    newState = PuzzleState.moveDown(state);
	    
	    /* Checks if new state exists and is not in state set. */
	    if(newState!=null && !stateSet.contains(newState)){
	      
	      /* Add the new state to the state set. */
	      stateSet.add(newState);
	      
	      /* Call recursive dfs method to explore next level of states in tree. */
	      dfs(newState, depth - 1);
	      
	      /* When goal exists, leave recursive search. */
	      if(goal != null)
	        return;
	      
	      /* Remove the new state from the set to save memory. */
	      stateSet.remove(newState);
	    }
	    
	    /* Try to move zero (blank space) left in the puzzle and set the new state if moves. */
	    newState = PuzzleState.moveLeft(state);
	    
	    /* Checks if new state exists and is not in state set. */
	    if(newState!=null && !stateSet.contains(newState)){
	      
	      /* Add the new state to the state set. */
	      stateSet.add(newState);
	      
	      /* Call recursive dfs method to explore next level of states in tree. */
	      dfs(newState, depth - 1);
	      
	      /* When goal exists, leave recursive search. */
	      if(goal != null)
	        return;
	      
	      /* Remove the new state from the set to save memory. */
	      stateSet.remove(newState);
	    }
	    
	    /* Try to move zero (blank space) right in the puzzle and set the new state if moves. */
	    newState = PuzzleState.moveRight(state);
	    
	    /* Checks if new state exists and is not in state set. */
	    if(newState!=null && !stateSet.contains(newState)){
	      
	      /* Add the new state to the state set. */
	      stateSet.add(newState);
	      
	      /* Call recursive dfs method to explore next level of states in tree. */
	      dfs(newState, depth - 1);
	      
	      /* When goal exists, leave recursive search. */
	      if(goal != null)
	        return;
	      
	      /* Remove the new state from the set to save memory. */
	      stateSet.remove(newState);
	    }
	  }
	  
	  /**
	   * Solves the input puzzle using depth-first search.
	   * Outputs the sequence of moves to get from initial, randomized state to goal state.
	   * Also tracks the amount of time taken to solve puzzle with this algorithm.
	   * 
	   * @param puzzle - the puzzle to solve with dfs
	   * @return the sequence of moves to reach the goal state from the initial, randomized state
	   */
	  public String solve(Puzzle puzzle) {
	    
	    /* Gets the start time of the search. */
	    long startTime = System.currentTimeMillis();
	    
	    /* Initializes goal state to not found. */
	    goal = null;
	    
	    /* Creates a new state from the input puzzle for solving. */
	    PuzzleState state = new PuzzleState(puzzle);
	    
	    /* Performs recursive depth-first search to find the goal state. */
	    dfs(state, MAX_DEPTH);
	    
	    /* Gets the difference between end time and start time to find total time taken to solve puzzle. */
	    time = System.currentTimeMillis() - startTime;
	    
	    Runtime run = Runtime.getRuntime();
	    run.gc();
	    room = run.totalMemory() - run.freeMemory();
	    
	    /* Returns the sequence of moves from initial, randomized puzzle state to goal state. */
	    return getSequence();
	  }
}
