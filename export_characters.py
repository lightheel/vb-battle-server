import json
import re

def load_json_file(filename):
    """Load and parse a JSON file."""
    try:
        with open(filename, 'r', encoding='utf-8') as f:
            return json.load(f)
    except Exception as e:
        print(f"Error loading {filename}: {e}")
        return None

def extract_character_data(character_data):
    """Extract character data from the JSON structure."""
    characters = []
    
    # The file contains a list of character objects
    for char_obj in character_data:
        # Extract the character string from the object
        char_str = str(char_obj)
        
        # Use regex to extract the character data
        pattern = r"charaId='([^']+)', charaNo=\d+, namekey='([^']+)', ipId=(\d+), ipName='[^']*', type=(\d+), phase=(\d+), hp=(\d+), ap=(\d+), bp=(\d+)"
        match = re.search(pattern, char_str)
        
        if match:
            chara_id = match.group(1)
            namekey = match.group(2)
            ip_id = int(match.group(3))
            char_type = int(match.group(4))
            phase = int(match.group(5))
            hp = int(match.group(6))
            ap = int(match.group(7))
            bp = int(match.group(8))
            
            characters.append({
                'charaId': chara_id,
                'namekey': namekey,
                'ipId': ip_id,
                'type': char_type,
                'phase': phase,
                'hp': hp,
                'ap': ap,
                'bp': bp
            })
    
    return characters

def get_english_name(namekey, text_data):
    """Get English name from VBTextMasterData.json using namekey."""
    for text_obj in text_data:
        text_str = str(text_obj)
        # Extract key and English name
        pattern = r"key='([^']+)', jp='[^']*', en='([^']*)'"
        match = re.search(pattern, text_str)
        
        if match and match.group(1) == namekey:
            return match.group(2)
    
    return namekey  # Return namekey if not found

def generate_java_code(characters, text_data, phase_filter=None, ip_id_filter=None):
    """Generate Java code for characters."""
    java_lines = []
    
    for char in characters:
        # Apply filters if specified
        if phase_filter is not None and char['phase'] != phase_filter:
            continue
        if ip_id_filter is not None and char['ipId'] != ip_id_filter:
            continue
        
        # Get English name
        english_name = get_english_name(char['namekey'], text_data)
        
        # Convert charaId to Java variable name (replace hyphens with underscores)
        var_name = char['charaId'].replace('-', '_')
        
        # Generate Java line
        java_line = f"    static public final Character {var_name} = new Character(\"{english_name}\",\"{char['namekey']}\",\"{char['charaId']}\",{char['phase']-3},{char['type']},{char['hp']}, {char['hp']}, {char['bp']}.0f,{char['ap']}.0f);"
        
        java_lines.append(java_line)
    
    return java_lines

def main():
    # Load JSON files
    print("Loading CharacterData.json...")
    character_data = load_json_file('exported_json/CharacterData.json')
    if not character_data:
        return
    
    print("Loading VBTextMasterData.json...")
    text_data = load_json_file('exported_json/VBTextMasterData.json')
    if not text_data:
        return
    
    # Extract character data
    print("Extracting character data...")
    characters = extract_character_data(character_data)
    print(f"Found {len(characters)} characters")
    
    # Generate Java code for different phases
    phases = [3, 4, 5, 6]  # Rookie, Champion, Ultimate, Mega
    phase_names = {3: "Rookie", 4: "Champion", 5: "Ultimate", 6: "Mega"}
    
    for phase in phases:
        print(f"\nGenerating {phase_names[phase]} characters (Phase {phase})...")
        java_lines = generate_java_code(characters, text_data, phase_filter=phase, ip_id_filter=1)
        
        if java_lines:
            # Write to file
            filename = f"VB{phase_names[phase]}Stats.java"
            with open(filename, 'w', encoding='utf-8') as f:
                f.write(f"// VB {phase_names[phase]} Characters (Phase {phase}) - Generated from CharacterData.json (ipId=1 only)\n")
                for line in java_lines:
                    f.write(line + "\n")
            
            print(f"Generated {len(java_lines)} {phase_names[phase]} characters in {filename}")
        else:
            print(f"No {phase_names[phase]} characters found")

if __name__ == "__main__":
    main() 