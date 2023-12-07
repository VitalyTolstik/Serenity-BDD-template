mvn clean verify -Dcucumber.filter.tags="@WEB" -Denvironment="browserstack,browserstack_macos_firefox"

add to local run - 

-DBROWSERSTACK_USER=
-DBROWSERSTACK_KEY=



mvn clean verify -Dcucumber.filter.tags="@WEB" -Denvironment=local_safari -DBROWSERSTACK_USER= -DBROWSERSTACK_KEY= -Denvironment.name=dev -Dscreen.type=web


environment = "browserstack,browserstack_windows_chrome"
BROWSERSTACK_USER=""
BROWSERSTACK_KEY=""

environments {
browserstack {
webdriver {
driver = remote
remote.url = "https://"${BROWSERSTACK_USER}":"${BROWSERSTACK_KEY}"@hub.browserstack.com/wd/hub"
}
}
browserstack_windows_chrome {
webdriver {
capabilities {
browserName = "chrome"
"bstack:options" {
os = "Windows"
osVersion = "11"
browserVersion = "latest"
local = false
}
}
}
}
browserstack_macos_firefox {
webdriver {
capabilities {
browserName = "firefox"
"bstack:options" {
os = "OS X"
osVersion = "Big Sur"
browserVersion = "latest"
local = false
}
}
}
}
local_chrome {
webdriver {
driver = chrome
}
}
local_safari {
webdriver {
driver = safari
}
}
local_firefox {
webdriver {
driver = firefox
}
}
local_edge {
webdriver {
driver = edge
}
}
}



