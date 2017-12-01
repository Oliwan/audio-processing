#!/usr/bin/python

import sys, getopt
import os
import sys
import subprocess
from os.path import join
def main(argv):
 path = '/home/vale/Scrivania/audio/' 
 for folder, subs, files in os.walk(path):
  print "Entering",folder

  for filename in files:
   #print filename
   if filename.endswith(('.opus')) or filename.endswith(('.ogg'))or filename.endswith(('.waptt')):
	fname=os.path.join(folder, filename)
	
    	bashCommand="opusdec " +fname+ " " +fname+".raw"
    	process=subprocess.Popen(bashCommand, stdout=subprocess.PIPE, shell=True)
    	output, error=process.communicate()
   	if error:
    	 print error
 print "Done."

if __name__ == "__main__":
   main(sys.argv[1:])

