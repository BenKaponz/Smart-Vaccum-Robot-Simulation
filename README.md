# 🤖 GurionRock Pro Max Ultra Over 9000  
**Concurrent Perception & Mapping System – SPL225 @ BGU**

A modular simulation of an autonomous vacuum robot’s perception and mapping system, developed as part of the **Systems Programming Lab** at Ben-Gurion University.  
The system models real-time sensor fusion using **multithreaded Java Microservices** and a custom **MessageBus framework**.

---

## 🔧 Technologies Used
- **Java 8** – Core implementation using threads, lambdas, and generics  
- **Custom MessageBus** – Publish/subscribe, event routing & futures  
- **Multithreading & Synchronization** – Thread-per-service model  
- **GSON** – For JSON parsing of input sensor data  
- **JUnit** – Test-driven development for core components  
- **Maven** – Build automation and dependency resolution  
- **Docker-compatible** – Tested in isolated environments  

---

## 💡 Project Structure

### `bgu.spl.mics.application`
- **`messages/`**  
  Contains all system messages (Events and Broadcasts) used in the pub/sub framework:
  - `DetectObjectsEvent.java`
  - `TrackedObjectsEvent.java`
  - `PoseEvent.java`
  - `TickBroadcast.java`
  - `CrashedBroadcast.java`, `TerminatedBroadcast.java`

- **`objects/`**  
  Data representations of sensors, tracked objects, the robot pose, and statistics:
  - `Camera.java`, `LiDarWorkerTracker.java`, `GPSIMU.java`
  - `DetectedObject.java`, `TrackedObject.java`, `LandMark.java`
  - `StampedCloudPoints.java`, `StampedDetectedObjects.java`
  - `FusionSlam.java`, `Pose.java`, `CloudPoint.java`, `StatisticalFolder.java`
  - `STATUS.java`, `LiDarDataBase.java`

- **`services/`**  
  Microservice implementations:
  - `CameraService.java`
  - `LiDarService.java`
  - `FusionSlamService.java`

---

## ✨ Features
- Fully event-driven concurrent design  
- Global system timer using `TickBroadcast`  
- Real-time pose-aware mapping with coordinate transformation  
- Supports multiple sensors with different frequencies  
- Fault detection & emergency shutdown using broadcast messages  
- Final JSON output with statistics and environment map  

---

## 🚀 How to Run

### 1. Build
```bash
mvn clean install
```

### 2. Execute
```bash
java -jar target/BGRock.jar path/to/config.json
```

### 3. Output
- Creates `output_file.json` in the same directory as the config:
  - Runtime stats
  - Final map (landmarks)
  - Crash report (if applicable)

---

## 🧪 Testing
```bash
mvn test
```
Includes unit tests for:
- `MessageBusImpl`
- `FusionSlam` (landmark transformation)
- `CameraService` / `LiDarService` behavior

---

## 📁 Directory Structure
```
src/
└── main/
    └── java/
        └── bgu/
            └── spl/
                └── mics/
                    └── application/
                        ├── messages/
                        ├── objects/
                        └── services/
```

---

## 📚 Course Information
- **Course:** SPL225 – Systems Programming Lab  
- **Institution:** Ben-Gurion University of the Negev  
- **Year:** 2025  
- **Project Grade:** 90  
- **Submission Format:** Maven project with JUnit tests and structured JSON I/O  
- **Environment:** Linux CS Lab, Docker-compatible  

---

## 🧑‍💻 Authors

**Ben Kapon**  
Student at BGU  
[LinkedIn](https://www.linkedin.com/in/your-ben-kapon-link)

**Itay Shaul**  
Student at BGU  
[LinkedIn](https://www.linkedin.com/in/your-itay-shaul-link)

---

## 📝 Important Note
This project was designed and tested on a **Docker-compatible environment**.  
Ensure file paths and JSON formats are valid when running in local or CI environments.
