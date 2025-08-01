# PowerShell script to export character data to Java format

# Load JSON files
Write-Host "Loading CharacterData.json..."
$characterDataJson = Get-Content "exported_json/CharacterData.json" | ConvertFrom-Json
$characterData = $characterDataJson.all_attributes.DataList

Write-Host "Loading VBTextMasterData.json..."
$textDataJson = Get-Content "exported_json/VBTextMasterData.json" | ConvertFrom-Json
$textData = $textDataJson.all_attributes.DataList

# Function to extract character data from the string format
function Extract-CharacterData {
    param($characterString)
    
    # Use regex to extract character data
    $pattern = "charaId='([^']+)', charaNo=\d+, namekey='([^']+)', ipId=(\d+), ipName='[^']*', type=(\d+), phase=(\d+), hp=(\d+), ap=(\d+), bp=(\d+)"
    
    if ($characterString -match $pattern) {
        return @{
            charaId = $matches[1]
            namekey = $matches[2]
            ipId = [int]$matches[3]
            type = [int]$matches[4]
            phase = [int]$matches[5]
            hp = [int]$matches[6]
            ap = [int]$matches[7]
            bp = [int]$matches[8]
        }
    }
    return $null
}

# Function to get English name from text data
function Get-EnglishName {
    param($namekey, $textData)
    
    foreach ($textObj in $textData) {
        $textStr = $textObj.ToString()
        $pattern = "key='([^']+)', jp='[^']*', en='([^']*)'"
        
        if ($textStr -match $pattern -and $matches[1] -eq $namekey) {
            return $matches[2]
        }
    }
    return $namekey
}

# Extract all character data
Write-Host "Extracting character data..."
$characters = @()

foreach ($charObj in $characterData) {
    $charData = Extract-CharacterData $charObj.ToString()
    if ($charData) {
        $characters += $charData
    }
}

Write-Host "Found $($characters.Count) characters"

# Generate Java code for different phases
$phases = @(3, 4, 5, 6)
$phaseNames = @{3 = "Rookie"; 4 = "Champion"; 5 = "Ultimate"; 6 = "Mega"}

foreach ($phase in $phases) {
    Write-Host "Generating $($phaseNames[$phase]) characters (Phase $phase)..."
    
    $javaLines = @()
    
    foreach ($char in $characters) {
        # Filter by phase and ipId=1
        if ($char.phase -eq $phase -and $char.ipId -eq 1) {
            $englishName = Get-EnglishName $char.namekey $textData
            $varName = $char.charaId -replace '-', '_'
            
            $javaLine = "    static public final Character $varName = new Character(`"$englishName`",`"$($char.namekey)`",`"$($char.charaId)`",$($char.phase-3),$($char.type),$($char.hp), $($char.hp), $($char.bp).0f,$($char.ap).0f);"
            $javaLines += $javaLine
        }
    }
    
    if ($javaLines.Count -gt 0) {
        $filename = "VB$($phaseNames[$phase])Stats.java"
        $javaLines | Out-File -FilePath $filename -Encoding UTF8
        Write-Host "Generated $($javaLines.Count) $($phaseNames[$phase]) characters in $filename"
    } else {
        Write-Host "No $($phaseNames[$phase]) characters found"
    }
}

Write-Host "Export complete!" 