#!/bin/sh

PACKAGE="GameOfLife"

POSITIONAL=()
while (( "$#" )); do
  case "$1" in
    -h|--help)
        echo "To play just run this file."
        echo "Enter plays the next step and 'end' or Ctrl-C ends the game. "
        echo "-i <file> | --input <file>   - Provide an input file"
        echo ""
        echo "input file structure is:"
        echo "<rows> <columns>"
        echo "<x> <y>"
        echo "Where each x and y are co-ordinates for the seeds."
        exit 0
      ;;
    -i|--input)
      if [ -n "$2" ] && [ ${2:0:1} != "-" ]; then
        INPUT=$2
        shift 2
      else
        echo "Error: Argument for $1 is missing" >&2
        exit 1
      fi
      ;;
    -*|--*=) # unsupported flags
      echo "Error: Unsupported flag $1" >&2
      exit 1
      ;;
    *) # preserve positional arguments
      POSITIONAL+="$1"
      shift
      ;;
  esac
done

javac *.java

if [ ! -d "$PACKAGE" ]; then
    mkdir $PACKAGE
fi

mv -f *.class $PACKAGE

if [ -z "$INPUT" ]; then
    java $PACKAGE.Driver ./ExampleInput.in
else
    java $PACKAGE.Driver $INPUT
fi

