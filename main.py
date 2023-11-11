import sys

def string_to_int(s):
    if "0" <= s <= "9":
        return int(s)
    elif 'A' <= s <= 'Z':
        return ord(s) - ord('A')
    elif 'a' <= s <= 'z':
        return ord(s) - ord('a') + 26

def parse_input():
    if len(sys.argv) != 4:
        print("Require 3 arguments")
        exit(0)
    
    information = []    
    for argv in sys.argv[1:]:
        table = argv.split(",")
        table_data = []
        for row in table:
            row_data = []
            for column in row:
                row_data.append(string_to_int(column))
            table_data.append(row_data)
        information.append(table_data)
    return information
        
def main():
    information = parse_input()
    connection = information[0]
    construction = information[1]
    desctruction = information[2]
    print(f"Connection: {connection}")
    print(f"Construction: {construction}")
    print(f"Destruction: {desctruction}")
if __name__ == "__main__":
    main()