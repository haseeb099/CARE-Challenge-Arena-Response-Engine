# CARE Challenge System – Java Coursework Project

A Java-based simulation/management system for the **CARE (Challenge Arena Response Engine)** coursework project. This application processes challenge data from `challengesAM.txt` (e.g., magic/fight/mystery quests against opponents like Borg, Huns, Ferengi) and handles scoring, player matching, or simulation logic. Built with Eclipse/IntelliJ hybrid setup for easy import.

## 🎯 Project Overview

The CARE system reads challenge data from a CSV-like file (`challengesAM.txt`) and simulates/processes quests:
- **Quest Types**: Magic, Fight, Mystery.
- **Opponents**: Borg, Huns, Ferengi, Vandals, etc.
- **Attributes**: Difficulty level (e.g., 3-10), Score/Reward (e.g., 45-300 points).
- **Example Data Row**: `Magic,Borg,3,100,` (Quest: Magic vs. Borg, Difficulty: 3, Reward: 100).

Key functionality (assumed based on structure — adjust if your code differs):
- Parse and load challenges into data structures (e.g., ArrayList of Challenge objects).
- Simulate player vs. opponent matches with scoring.
- Handle tests in `test/` and `myTests/` folders using JUnit.
- Output results like winner determination or total scores.

## 🚀 Features
- **Data Parsing**: Reads `challengesAM.txt` for quest/opponent/difficulty/reward.
- **OOP Design**: Classes for `Challenge`, `Opponent`, `Player` (in `src/`).
- **Testing**: JUnit suites in `test/` and `myTests/` for unit/integration coverage.
- **Build Configs**: Eclipse (`.project`, `.classpath`) + IntelliJ (`.iml`) for seamless import.
- **Error Handling**: Robust parsing for malformed data rows.

## 🏗️ Project Structure
CARE-Challenge-System-Java/
├── .project                  # Eclipse project config
├── .classpath               # Eclipse classpath
├── care-studentsIJ.iml      # IntelliJ module file
├── challengesAM.txt         # Sample challenge data (CSV-like)
├── src/                     # Main Java sources
│   ├── Challenge.java       # Quest logic (example)
│   ├── Opponent.java        # Enemy classes
│   └── ... (your core classes)
├── test/                    # JUnit unit tests
├── myTests/                 # Custom/integration tests
└── out/production/...       # Compiled output (auto-generated)


- **Dependencies**: JUnit (via `.iml`); pure Java otherwise (JDK 8+).
- **Total Files**: ~10+ (configs + sources + data + tests).

## 💻 Setup & Running

### Prerequisites
- **Java JDK**: 8 or higher.
- **IDE**: Eclipse (import as existing project) or IntelliJ (open `.iml`).

### Quick Start
1. **Clone the Repo**:
   ```bash
   git clone
   cd CARE-Challenge-System-Java
2.Import & Run in Eclipse:
File → Import → Existing Projects into Workspace → Select folder → Finish.
Right-click a test class in test/ → Run As → JUnit Test.

3.Import & Run in IntelliJ:
Open care-studentsIJ.iml → Let it index.
Run tests: Right-click test/ folder → Run 'Tests in test'.

4.Sample Run (e.g., main method or test):
Loads challengesAM.txt.
Outputs: "Quest: Magic vs. Borg (Diff: 3, Reward: 100)".

🔧 Key Java Concepts Demonstrated

File I/O: Parsing CSV-like data with BufferedReader/Scanner.
Collections: ArrayList<Challenge> for dynamic storage.
OOP: Encapsulation in classes; polymorphism if extended.
Testing: JUnit assertions for data validation.
Build Tools: Eclipse/IntelliJ configs for cross-IDE support.

🧪 Testing

Run all tests: Covers data loading, quest simulation, edge cases (e.g., invalid difficulty).
Example: Asserts "Fight vs. Huns yields 120 points".

📈 Future Enhancements

GUI for quest selection (Swing/JavaFX).
Database integration (SQLite for persistent challenges).
Multiplayer simulation.

📄 License & Credits

License: MIT (educational use).
Author: Muhammad Haseeb Rafique

🤝 Contributing
Fork for extensions! Issues welcome for bugs or features.
