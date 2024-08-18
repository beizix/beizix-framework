#!/bin/sh

GREEN='\033[0;32m'
NC='\033[0m' # No Color

path=$1
domainNm=$2
url=$3

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
showCnt=false
get=false
post=false
rest=false

set -- $(getopt -o psgour --long pageable,show,get,post,url,rest -- "$@")
for word in "$@"
do
    case $word in
       -p | --pageable) pageable=true; break ;;
    esac
done

for word in "$@"
do
    case $word in
       -s | --show) showCnt=true; ;;
       -r | --rest) rest=true; ;;
    esac   
done

for word in "$@"
do
    case $word in
       -g | --get) get=true; break ;;
       -o | --post) post=true; break ;;
    esac
done

modelPath="${path}/model"

pkgPath=${path##*/src/main/java/}
basePackage=${pkgPath//\//\.}

echo "basePackage=$basePackage"
echo "url=${url}"

webPackage=${pkgPath//\//\.}

cmdCreatePath="${pkgPath}/model"
cmdPackage=${cmdCreatePath//\//\.}

# read config.properties
properties=./conf/config.properties

function prop {
    grep "${1}" ${properties} | cut -d'=' -f2
}

pageRoot=$(prop 'web.page.root')
pageExt=$(prop 'web.page.ext')
# end - read config.properties

rm -rf "${path}"

mkdir -p "${modelPath}"

function showContent() {
    if $showCnt; then
        echo -e "${GREEN}\n"
        cat $1
        echo -e "${NC}"
    fi
}

function createEntity(){
    tmpl=$1
    pkg=$2
    domainNm=$3
    fullEntityPath=$4

    echo -e "\n"
    echo "Creating a request value object.."

    cp -f "templates/web/${tmpl}" ./
    sed -i "s/#package/${pkg}/" "${tmpl}"
    sed -i "s/#domainNm/${domainNm}/" "${tmpl}"
    mv -f "${tmpl}" "${fullEntityPath}"

    echo -e "${fullEntityPath} is created."
}

function createGetController() {
  tmpl=$1
  pkg=$2
  domainNm=$3
  cmdPkg=$4
  fullEntityPath=$5
  targetUrl=$6
  page=${targetUrl/\/}

  echo -e "\n"
  echo "Creating a [get] controller that is mapped to '${targetUrl}'"

  cp -f "templates/web/${tmpl}" ./
  sed -i "s/#package/${pkg}/g" "${tmpl}"
  sed -i "s/#domainNm/${domainNm}/g" "${tmpl}"
  sed -i "s/#cmdPackage/${cmdPkg}/g" "${tmpl}"
  sed -i "s@#url@${targetUrl}@g" "${tmpl}"
  sed -i "s@#page@${page}@g" "${tmpl}"
  mv -f "${tmpl}" "${fullEntityPath}"

  echo "${fullEntityPath} is created."

  createPage
}

function createPage(){
  # page template root 경로 얻기
  modulePath=${path%%/src/main/java/*}
  templateRoot="${modulePath}${pageRoot}"

  pagePath="${url%/*}"
  pageFile="${url##*/}${pageExt}"

  echo "templateRoot=${templateRoot}"
  echo "pagePath=${pagePath}"
  echo "pageFile=${pageFile}"

  mkdir -p "${templateRoot}${pagePath}"

  cp -f "templates/web/templates/page.tmpl" ./
  sed -i "s@#url@${url}@g" ./page.tmpl
  mv -f ./page.tmpl "${templateRoot}${pagePath}/${pageFile}"
}

createEntity reqVO.tmpl "${cmdPackage}" "${domainNm}" "${modelPath}/${domainNm}ReqVO.java"
showContent "${modelPath}/${domainNm}ReqVO.java"

if ! $rest; then

    if $get; then
      createGetController getController.tmpl "${webPackage}" "${domainNm}" "${cmdPackage}" "${path}/${domainNm}Controller.java" "${url}"
      showContent "${path}/${domainNm}Controller.java"
#    elif $post; then

    fi
fi




#
#function createInterface(){
#    tmpl=$1
#    pkg=$2
#    domainNm=$3
#    cmdPkg=$4
#    fullEntityPath=$5
#
#    cp -f "templates/${tmpl}" ./
#    sed -i "s/#package/${pkg}/g" "${tmpl}"
#    sed -i "s/#domainNm/${domainNm}/g" "${tmpl}"
#    sed -i "s/#cmdPackage/${cmdPkg}/g" "${tmpl}"
#    mv -f "${tmpl}" "${fullEntityPath}"
#
#    echo "${fullEntityPath} is created."
#}
#
#function createImpl(){
#    tmpl=$1
#    pkg=$2
#    domainNm=$3
#    cmdPkg=$4
#    portPkg=$5
#    fullEntityPath=$6
#
#    cp -f "templates/${tmpl}" ./
#    sed -i "s/#package/${pkg}/g" "${tmpl}"
#    sed -i "s/#domainNm/${domainNm}/g" "${tmpl}"
#    sed -i "s/#portPackage/${portPkg}/g" "${tmpl}"
#    sed -i "s/#cmdPackage/${cmdPkg}/g" "${tmpl}"
#    mv -f "${tmpl}" "${fullEntityPath}"
#
#    echo "${fullEntityPath} is created."
#}
#
#
#
#
#
#
#echo -e "\n"
#
#echo "Creating a Command object.."
#
#createEntity classCommand.tmpl ${cmdPackage} ${domainNm} "${domainFullPath}/${domainNm}Cmd.java"
#showContent "${domainFullPath}/${domainNm}Cmd.java"
#
#if ! $void; then
#    echo -e "\n"
#    echo "Creating a Domain object.."
#
#    createEntity classReturn.tmpl ${cmdPackage} ${domainNm} "${domainFullPath}/${domainNm}.java"
#    showContent "${domainFullPath}/${domainNm}.java"
#fi
#
#portCreatePath=$pkgPath$portPath
#portPackage=${portCreatePath//\//\.}
#applCreatePath=$portCreatePath$applicationPath
#applPackage=${applCreatePath//\//\.}
#daoCreatePath=$pkgPath$persistPath
#daoPackage=${daoCreatePath//\//\.}
#
#echo -e "\n"
#
#if $pageable; then
#    echo "Creating a PortIn interface that returns Pageable.."
#
#    createInterface pageablePortIn.tmpl ${portPackage} ${domainNm} ${cmdPackage} "${path}${portPath}/${domainNm}PortIn.java"
#    showContent "${path}${portPath}/${domainNm}PortIn.java"
#
#    echo -e "\n"
#
#    echo "Creating a PortOut interface that returns Pageable.."
#
#    createInterface pageablePortOut.tmpl ${portPackage} ${domainNm} ${cmdPackage} "${path}${portPath}/${domainNm}PortOut.java"
#    showContent "${path}${portPath}/${domainNm}PortOut.java"
#
#    if $implements; then
#        echo -e "\n"
#        echo "Creating a Service that implements ${domainNm}PortIn.."
#
#        createImpl pageableService.tmpl ${applPackage} ${domainNm} ${cmdPackage} ${portPackage} "${path}${portPath}${applicationPath}/${domainNm}Service.java"
#        showContent "${path}${portPath}${applicationPath}/${domainNm}Service.java"
#
#        echo -e "\n"
#        echo "Creating a DAO that implements ${domainNm}PortOut.."
#
#        createImpl pageableDao.tmpl ${daoPackage} ${domainNm} ${cmdPackage} ${portPackage} "${path}${persistPath}/${domainNm}Dao.java"
#        showContent "${path}${persistPath}/${domainNm}Dao.java"
#    fi
#
#elif $list; then
#    echo "Creating a interface that returns List."
#
#    createInterface listPortIn.tmpl ${portPackage} ${domainNm} ${cmdPackage} "${path}${portPath}/${domainNm}PortIn.java"
#    showContent "${path}${portPath}/${domainNm}PortIn.java"
#
#    echo -e "\n"
#
#    echo "Creating a PortOut interface that returns Pageable.."
#
#    createInterface listPortOut.tmpl ${portPackage} ${domainNm} ${cmdPackage} "${path}${portPath}/${domainNm}PortOut.java"
#    showContent "${path}${portPath}/${domainNm}PortOut.java"
#
#    if $implements; then
#        echo -e "\n"
#        echo "Creating a Service that implements ${domainNm}PortIn.."
#
#        createImpl listService.tmpl ${applPackage} ${domainNm} ${cmdPackage} ${portPackage} "${path}${portPath}${applicationPath}/${domainNm}Service.java"
#        showContent "${path}${portPath}${applicationPath}/${domainNm}Service.java"
#
#        echo -e "\n"
#        echo "Creating a DAO that implements ${domainNm}PortOut.."
#
#        createImpl listDao.tmpl ${daoPackage} ${domainNm} ${cmdPackage} ${portPackage} "${path}${persistPath}/${domainNm}Dao.java"
#        showContent "${path}${persistPath}/${domainNm}Dao.java"
#    fi
#
#elif $void; then
#    echo "Creating a PortIn interface that has no return."
#
#    createInterface voidPortIn.tmpl ${portPackage} ${domainNm} ${cmdPackage} "${path}${portPath}/${domainNm}PortIn.java"
#    showContent "${path}${portPath}/${domainNm}PortIn.java"
#
#    echo -e "\n"
#
#    echo "Creating a PortOut interface that has no return."
#
#    createInterface voidPortOut.tmpl ${portPackage} ${domainNm} ${cmdPackage} "${path}${portPath}/${domainNm}PortOut.java"
#    showContent "${path}${portPath}/${domainNm}PortOut.java"
#
#    if $implements; then
#        echo -e "\n"
#        echo "Creating a Service that implements ${domainNm}PortIn.."
#
#        createImpl voidService.tmpl ${applPackage} ${domainNm} ${cmdPackage} ${portPackage} "${path}${portPath}${applicationPath}/${domainNm}Service.java"
#        showContent "${path}${portPath}${applicationPath}/${domainNm}Service.java"
#
#        echo -e "\n"
#        echo "Creating a DAO that implements ${domainNm}PortOut.."
#
#        createImpl voidDao.tmpl ${daoPackage} ${domainNm} ${cmdPackage} ${portPackage} "${path}${persistPath}/${domainNm}Dao.java"
#        showContent "${path}${persistPath}/${domainNm}Dao.java"
#    fi
#
#else
#    echo "Creating a PortIn interface that returns ${domainNm}."
#
#    createInterface portIn.tmpl ${portPackage} ${domainNm} ${cmdPackage} "${path}${portPath}/${domainNm}PortIn.java"
#    showContent "${path}${portPath}/${domainNm}PortIn.java"
#
#    echo -e "\n"
#
#    echo "Creating a PortOut interface that returns ${domainNm}."
#
#    createInterface portOut.tmpl ${portPackage} ${domainNm} ${cmdPackage} "${path}${portPath}/${domainNm}PortOut.java"
#    showContent "${path}${portPath}/${domainNm}PortOut.java"
#
#    if $implements; then
#        echo -e "\n"
#        echo "Creating a Service that implements ${domainNm}PortIn.."
#
#        createImpl service.tmpl ${applPackage} ${domainNm} ${cmdPackage} ${portPackage} "${path}${portPath}${applicationPath}/${domainNm}Service.java"
#        showContent "${path}${portPath}${applicationPath}/${domainNm}Service.java"
#
#        echo -e "\n"
#        echo "Creating a DAO that implements ${domainNm}PortOut.."
#
#        createImpl dao.tmpl ${daoPackage} ${domainNm} ${cmdPackage} ${portPackage} "${path}${persistPath}/${domainNm}Dao.java"
#        showContent "${path}${persistPath}/${domainNm}Dao.java"
#    fi
#fi