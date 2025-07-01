# CS460Project
This project is an algorithm that optimizes farming routes in open-world action role-play games, like Genshin Impact.
It calculates the most efficient path to complete objectives based on user input.

## Features
- Calculates optimal objective routes
- Supports input customization
- Outputs efficient route plans

## Getting Started

### Prerequisites
- Java 22+
- Intellij IDEA (recommended)

### How to Run
Step 1: Clone this repo.
```bash
git clone https://github.com/briannafs/CS460Project.git
cd CS460Project
```

Step 2: Open project in preferred IDE and set SDK/JDK to Java 22. 

Step 3: Open and run the `DynamicPriorityRouting.java` in the src/ folder.
- You will be prompted to input 3 values per objective for 4 objectives.
- You will then be prompted to input 1 value per edge/travel cost. 

## Sample Usage

Objective 1 (Node A): 7 2 3  
Objective 2 (Node B): 4 3 1  
Objective 3 (Node C): 3 3 2  
Objective 4 (Node Goal): 2 1 4  

Edge from Start to A: 4  
Edge from Start to B: 2  
Edge from B to C: 2  
Edge from A to Goal: 6  
Edge from C to Goal: 3  

Expected Result: Start -> B -> C -> Goal

## Project Structure
CS460Project/  
├── src/  
│   └── DynamicPriorityRouting.java   # Main algorithm implementation  
├── .idea/                            # IntelliJ project settings  
│   ├── codeStyles/  
│   └── libraries/  
├── .gitignore                        # Git ignore rules  
├── GenshinFarmingOptimizer.iml       # IntelliJ project file  
├── README.md                         # Project overview and instructions  

