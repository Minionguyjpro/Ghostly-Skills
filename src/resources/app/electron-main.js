'use strict';
const {app, BrowserWindow, Menu, shell, screen} = require('electron');
const path = require('path');

const isWindows = process.platform === 'win32';
const isMac = process.platform === 'darwin';
const isLinux = process.platform === 'linux';

if (isMac) {
  // TODO
  Menu.setApplicationMenu(Menu.buildFromTemplate([
    { role: 'appMenu' },
    { role: 'fileMenu' },
    { role: 'editMenu' },
    { role: 'viewMenu' },
    { role: 'windowMenu' },
    { role: 'help' }
  ]));
} else {
  Menu.setApplicationMenu(null);
}

const isSafeOpenExternal = (url) => {
  try {
    const parsedUrl = new URL(url);
    return parsedUrl.protocol === 'https:' || parsedUrl.protocol === 'http:';
  } catch (e) {
    // ignore
  }
  return false;
};

const createWindow = () => {
  const options = {
    backgroundColor: "#000000",
    width: 480,
    height: 360,
    title: "Ghostly skills",
    useContentSize: true,
    minWidth: 50,
    minHeight: 50,
    icon: path.resolve(__dirname, "icon.png"),
    webPreferences: {
      contextIsolation: true,
      nodeIntegration: false,
      sandbox: true
    },
    show: true
  };

  const activeScreen = screen.getDisplayNearestPoint(screen.getCursorScreenPoint());
  const bounds = activeScreen.workArea;
  options.x = bounds.x + ((bounds.width - options.width) / 2);
  options.y = bounds.y + ((bounds.height - options.height) / 2);

  const window = new BrowserWindow(options);
  window.loadFile(path.resolve(__dirname, './index.html'));
};

app.enableSandbox();

app.on('web-contents-created', (event, contents) => {
  contents.setWindowOpenHandler((details) => {
    if (isSafeOpenExternal(details.url)) {
      setImmediate(() => {
        shell.openExternal(details.url);
      });
    }
    return {action: 'deny'};
  });
  contents.on('will-navigate', (e, url) => {
    e.preventDefault();
    if (isSafeOpenExternal(url)) {
      shell.openExternal(url);
    }
  });
});

app.on('window-all-closed', () => {
  app.quit();
});

app.whenReady().then(() => {
  createWindow();
  app.on('activate', () => {
    if (BrowserWindow.getAllWindows().length === 0) createWindow();
  });
});
