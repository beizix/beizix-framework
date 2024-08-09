#!/bin/sh

GREEN='\033[0;32m'
NC='\033[0m' # No Color

path=$1
domainNm=$2

cat << "EOF"
                                    
                            /          /o       
 _   _   __  o ____  _,    /____ __ __/ '. . _  
/_)_/_)_/ (_<_/ / <_(_)_  /_)(_)(_)(_/_ (_/_/_)_
   /                 /|                    /    
  '                 |/                    '     

The MIT License (MIT)

Copyright (c) 2024 YoungMok Kim <indiemaru@gmail.com>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.

EOF

pageable=false
list=false
void=false
implements=false
showCnt=false

set -- $(getopt -o plvis --long pageable,list,void,impl,show -- "$@")
for word in "$@"
do
    case $word in
       -p | --pageable) pageable=true; break ;;
       -l | --list) list=true; break ;;
       -v | --void) void=true; break ;;
       -i | --implements) implements=true; break ;;
    esac   
done

for word in "$@"
do
    case $word in
       -i | --impl) implements=true; ;;
       -s | --show) showCnt=true; ;;
    esac   
done

portPath="/ports"
applicationPath="/application"
domainPath="/domain"
persistPath="/adapters/persistence"
domainFullPath=$path$portPath$applicationPath$domainPath

rm -rf $path

mkdir -p $path$portPath
mkdir -p $domainFullPath
mkdir -p $path/adapters
mkdir -p $path/adapters/web
mkdir -p $path$persistPath

function createEntity(){
    tmpl=$1
    pkg=$2
    domainNm=$3
    fullEntityPath=$4

    cp -f "templates/${tmpl}" ./
    sed -i "s/#package/${pkg}/" $tmpl
    sed -i "s/#domainNm/${domainNm}/" $tmpl
    mv -f $tmpl $fullEntityPath

    echo -e "${fullEntityPath} is created."
}

function createInterface(){
    tmpl=$1
    pkg=$2
    domainNm=$3
    cmdPkg=$4
    fullEntityPath=$5

    cp -f "templates/${tmpl}" ./
    sed -i "s/#package/${pkg}/g" $tmpl
    sed -i "s/#domainNm/${domainNm}/g" $tmpl
    sed -i "s/#cmdPackage/${cmdPkg}/g" $tmpl
    mv -f $tmpl $fullEntityPath

    echo "${fullEntityPath} is created."
}

function createImpl(){
    tmpl=$1
    pkg=$2
    domainNm=$3
    cmdPkg=$4
    portPkg=$5
    fullEntityPath=$6

    cp -f "templates/${tmpl}" ./
    sed -i "s/#package/${pkg}/g" $tmpl
    sed -i "s/#domainNm/${domainNm}/g" $tmpl
    sed -i "s/#portPackage/${portPkg}/g" $tmpl
    sed -i "s/#cmdPackage/${cmdPkg}/g" $tmpl
    mv -f $tmpl $fullEntityPath

    echo "${fullEntityPath} is created."
}

function showContent() {
    if $showCnt; then
        
        echo -e "${GREEN}\n"
        cat $1
        echo -e "${NC}"
    fi    
}


pkgPath=${path##*/src/main/java/}
basePackage=${pkgPath//\//\.}

echo "basePackage=$basePackage"

cmdCreatePath=$pkgPath$portPath$applicationPath$domainPath
cmdPackage=${cmdCreatePath//\//\.}

echo -e "\n"

echo "Creating a Command object.."

createEntity classCommand.tmpl ${cmdPackage} ${domainNm} "${domainFullPath}/${domainNm}Cmd.java"
showContent "${domainFullPath}/${domainNm}Cmd.java"

if ! $void; then
    echo -e "\n"
    echo "Creating a Domain object.."

    createEntity classReturn.tmpl ${cmdPackage} ${domainNm} "${domainFullPath}/${domainNm}.java"
    showContent "${domainFullPath}/${domainNm}.java"
fi

portCreatePath=$pkgPath$portPath
portPackage=${portCreatePath//\//\.}
applCreatePath=$portCreatePath$applicationPath
applPackage=${applCreatePath//\//\.}
daoCreatePath=$pkgPath$persistPath
daoPackage=${daoCreatePath//\//\.}

echo -e "\n"

if $pageable; then
    echo "Creating a PortIn interface that returns Pageable.."

    createInterface pageablePortIn.tmpl ${portPackage} ${domainNm} ${cmdPackage} "${path}${portPath}/${domainNm}PortIn.java"
    showContent "${path}${portPath}/${domainNm}PortIn.java"

    echo -e "\n"

    echo "Creating a PortOut interface that returns Pageable.."

    createInterface pageablePortOut.tmpl ${portPackage} ${domainNm} ${portPackage} "${path}${portPath}/${domainNm}PortOut.java"
    showContent "${path}${portPath}/${domainNm}PortOut.java"

    if $implements; then
        echo -e "\n"
        echo "Creating a Service that implements ${domainNm}PortIn.."

        createImpl pageableService.tmpl ${applPackage} ${domainNm} ${portPackage} ${cmdPackage} "${path}${portPath}${applicationPath}/${domainNm}Service.java"
        showContent "${path}${portPath}${applicationPath}/${domainNm}Service.java"

        echo -e "\n"
        echo "Creating a DAO that implements ${domainNm}PortOut.."

        createImpl pageableDao.tmpl ${daoPackage} ${domainNm} ${portPackage} ${cmdPackage} "${path}${persistPath}/${domainNm}Dao.java"
        showContent "${path}${persistPath}/${domainNm}Dao.java"
    fi

elif $list; then    
    echo "Creating a interface that returns List."

    createInterface listPortIn.tmpl ${portPackage} ${domainNm} ${cmdPackage} "${path}${portPath}/${domainNm}PortIn.java"
    showContent "${path}${portPath}/${domainNm}PortIn.java"

    echo -e "\n"

    echo "Creating a PortOut interface that returns Pageable.."

    createInterface listPortOut.tmpl ${portPackage} ${domainNm} ${cmdPackage} "${path}${portPath}/${domainNm}PortOut.java"
    showContent "${path}${portPath}/${domainNm}PortOut.java"

    if $implements; then
        echo -e "\n"
        echo "Creating a Service that implements ${domainNm}PortIn.."

        createImpl listService.tmpl ${applPackage} ${domainNm} ${portPackage} ${cmdPackage} "${path}${portPath}${applicationPath}/${domainNm}Service.java"
        showContent "${path}${portPath}${applicationPath}/${domainNm}Service.java"

        echo -e "\n"
        echo "Creating a DAO that implements ${domainNm}PortOut.."

        createImpl listDao.tmpl ${daoPackage} ${domainNm} ${portPackage} ${cmdPackage} "${path}${persistPath}/${domainNm}Dao.java"
        showContent "${path}${persistPath}/${domainNm}Dao.java"
    fi

elif $void; then    
    echo "Creating a PortIn interface that has no return."

    createInterface voidPortIn.tmpl ${portPackage} ${domainNm} ${cmdPackage} "${path}${portPath}/${domainNm}PortIn.java"
    showContent "${path}${portPath}/${domainNm}PortIn.java"

    echo -e "\n"

    echo "Creating a PortOut interface that has no return."

    createInterface voidPortOut.tmpl ${portPackage} ${domainNm} ${cmdPackage} "${path}${portPath}/${domainNm}PortOut.java"
    showContent "${path}${portPath}/${domainNm}PortOut.java"

    if $implements; then
        echo -e "\n"
        echo "Creating a Service that implements ${domainNm}PortIn.."

        createImpl voidService.tmpl ${applPackage} ${domainNm} ${portPackage} ${cmdPackage} "${path}${portPath}${applicationPath}/${domainNm}Service.java"
        showContent "${path}${portPath}${applicationPath}/${domainNm}Service.java"

        echo -e "\n"
        echo "Creating a DAO that implements ${domainNm}PortOut.."

        createImpl voidDao.tmpl ${daoPackage} ${domainNm} ${portPackage} ${cmdPackage} "${path}${persistPath}/${domainNm}Dao.java"
        showContent "${path}${persistPath}/${domainNm}Dao.java"
    fi

else
    echo "Creating a PortIn interface that returns ${domainNm}."     

    createInterface portIn.tmpl ${portPackage} ${domainNm} ${cmdPackage} "${path}${portPath}/${domainNm}PortIn.java"
    showContent "${path}${portPath}/${domainNm}PortIn.java"

    echo -e "\n"

    echo "Creating a PortOut interface that returns ${domainNm}."

    createInterface portOut.tmpl ${portPackage} ${domainNm} ${cmdPackage} "${path}${portPath}/${domainNm}PortOut.java"
    showContent "${path}${portPath}/${domainNm}PortOut.java"

    if $implements; then
        echo -e "\n"
        echo "Creating a Service that implements ${domainNm}PortIn.."

        createImpl service.tmpl ${applPackage} ${domainNm} ${portPackage} ${cmdPackage} "${path}${portPath}${applicationPath}/${domainNm}Service.java"
        showContent "${path}${portPath}${applicationPath}/${domainNm}Service.java"

        echo -e "\n"
        echo "Creating a DAO that implements ${domainNm}PortOut.."

        createImpl dao.tmpl ${daoPackage} ${domainNm} ${portPackage} ${cmdPackage} "${path}${persistPath}/${domainNm}Dao.java"
        showContent "${path}${persistPath}/${domainNm}Dao.java"
    fi
fi