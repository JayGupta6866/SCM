console.log("Script Loadded");

// set theme to local storage
function settheme(theme) {
  localStorage.setItem("theme", theme);
}

// get theme from local storage
function getTheme() {
  let theme = localStorage.getItem("theme");
  // if (theme) return theme;
  // else return "light";
  return theme ? theme : "light";
}

// change theme
let curr_theme = getTheme();
changeTheme();
console.log(curr_theme);

// document.addEventListener('DOMContentLoaded',() => {
//     changeTheme();
// })

function changeTheme() {
  document.querySelector("html").classList.add(curr_theme);

  const changeThemeButton = document.getElementById("theme_alter");

  changeThemeButton.querySelector("span").textContent =
    curr_theme == "light" ? "Dark" : "Light";

  changeThemeButton.addEventListener("click", (event) => {
    const oldTheme = curr_theme;
    console.log("Button Clicked");

    if (curr_theme == "dark") {
      curr_theme = "light";
    } else {
      curr_theme = "dark";
    }
    settheme(curr_theme);

    document.querySelector("html").classList.remove(oldTheme);
    document.querySelector("html").classList.add(curr_theme);

    changeThemeButton.querySelector("span").textContent =
      curr_theme == "light" ? "Dark" : "Light";
  });
}
