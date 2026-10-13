# CS522I2C – Data Communication and Networking Laboratory

Java programs for the DCN lab (Dept. of Information Science & Engineering, SCEM, Mangaluru).

| # | Experiment | Folder | Files |
|---|------------|--------|-------|
| 1 | Error detection using CRC | `01_CRC` | `crc.java` |
| 2 | Congestion control – Leaky Bucket | `02_LeakyBucket` | `leaky_bucket.java` |
| 3 | TCP iterative client-server | `03_TCP` | `tcpser.java`, `tcpcl.java` |
| 4 | UDP iterative client-server | `04_UDP` | `udpser.java`, `udpcl.java` |

## How to run

Requires JDK (`javac` and `java`).

### 1. CRC
```bash
cd 01_CRC
javac crc.java
java crc
```
Sample input: data `1 0 1 1 0 0 1`, divisor `1 0 1` -> CRC code `101100111`.

### 2. Leaky Bucket
```bash
cd 02_LeakyBucket
javac leaky_bucket.java
java leaky_bucket
```
Sample input: bucket size `10`, output rate `4`, seconds `10`. Packet arrivals are random.

### 3. TCP (use two terminals)
```bash
cd 03_TCP
javac tcpser.java tcpcl.java
echo "Hello from file" > hai.txt

# Terminal 1
java tcpser

# Terminal 2
java tcpcl        # type: hai.txt
```

### 4. UDP (use two terminals)
```bash
cd 04_UDP
javac udpser.java udpcl.java

# Terminal 1
java udpser       # then type a reply message

# Terminal 2
java udpcl
```
Always start the **server first**, then the client.
