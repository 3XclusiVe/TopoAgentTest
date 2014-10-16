

    //import the classes that we will use
    import org.hyperic.sigar.CpuInfo;
    import org.hyperic.sigar.Sigar;
    import org.hyperic.sigar.SigarException;

    //declare the CPUInfo class
    public class CPUInfo {

        /**
         * declare a constructor for CPUINFO
         *
         * Remember that a constructor normally is meant to set the class up...in
         * this case we are bending the rules just to make the example clearer.
         * Really the constructor should be empty and we should be doing this in a
         * method.
         */
        public CPUInfo() {
// declare the Sigar class (we need this because it gathers the
// statistics)
            Sigar sigar = new Sigar();

// the output string is just going to hold our output string
            String output = "";

// an array of CPU Info classes. The CPUInfo class is a blank data
// holder class. Just like string it can hold any data.
            CpuInfo[] cpuInfoList = null;

// the try catch block means that if we get an error we are notified
            try {
// get the CPU information from the sigar library
                cpuInfoList = sigar.getCpuInfoList();

// if something foes wrong
            } catch (SigarException e) {
// write a description of the problem to the output
                e.printStackTrace();

// exit the constructor
                return;
            }

// for each item in the cpu info array
            for (CpuInfo info : cpuInfoList) {
// add the data to the output ( output += "something" means add
// "something" to the end of output)
                output += "\nCPU\n";
                output += "Vendor: " + info.getVendor() + "\n";
                output += "Core " + info.getCoresPerSocket() + "\n";
                output += "Total Cores " + info.getTotalCores() + "\n";
                output += "Total Sockets " + info.getTotalSockets() + "\n";
                output += "Cash " + info.getCacheSize() + "\n";
                output += "Model " + info.getModel() + "\n";
                output += "Clock: " + info.getMhz() + "Mhz\n";
            }

// finally, print the data to the output
            System.out.println(output);
        }

        public static void main(String[] args) {
            CPUInfo main = new CPUInfo();
        }
    }


