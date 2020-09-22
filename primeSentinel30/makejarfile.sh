
# manifest.txt

# Main-Class: primeSentinel30.Terminal
#


javac -d bin/ primeSentinel30/Terminal.java 
jar cfm SentinelWithTerminal.jar manifest.txt -C bin primeSentinel30
