document.addEventListener("DOMContentLoaded", function () {
    const togglePassword = document.getElementById("togglePassword");
    if (togglePassword) {
        togglePassword.addEventListener("click", function () {
            let passwordInput = document.getElementById("password");
            if (passwordInput.type === "password") {
                passwordInput.type = "text";
                this.classList.replace("fi-rr-lock", "fi-rr-unlock");
            } else {
                passwordInput.type = "password";
                this.classList.replace("fi-rr-unlock", "fi-rr-lock");
            }
        });
    }

    const container = document.getElementById("container");
    if (container) {
        const colors = ["#00457f", "#2f8ee0"];
        for (let i = 0; i < 35; i++) {
            let circle = document.createElement("div");
            let size = Math.random() * 30 + 60;
            circle.style.width = size + "px";
            circle.style.height = size + "px";
            circle.classList.add("circle");
            circle.style.backgroundColor = colors[Math.floor(Math.random() * colors.length)];
            circle.style.left = Math.random() * window.innerWidth + "px";
            circle.style.top = Math.random() * window.innerHeight + "px";
            container.appendChild(circle);
        }
    }

    let isForm1Active = true;
    const switchLinks = document.querySelectorAll(".switchLink");
    if (switchLinks.length > 0) {
        switchLinks.forEach(button => {
            button.addEventListener("click", function () {
                if (isForm1Active) {
                    document.querySelector(".loginFormContainer").classList.add("hidden");
                    setTimeout(() => {
                        document.querySelector(".loginFormContainer").style.zIndex = "1";
                        document.querySelector(".supportFormContainer").style.zIndex = "2";
                        document.querySelector(".loginFormContainer").classList.remove("hidden");
                    }, 500);
                } else {
                    document.querySelector(".supportFormContainer").classList.add("hidden");
                    setTimeout(() => {
                        document.querySelector(".supportFormContainer").style.zIndex = "1";
                        document.querySelector(".loginFormContainer").style.zIndex = "2";
                        document.querySelector(".supportFormContainer").classList.remove("hidden");
                    }, 500);
                }
                isForm1Active = !isForm1Active;
            });
        });
    }

    const images = document.querySelectorAll(".loginPageImage");
    if (images.length > 0) {
        const randomIndex = Math.floor(Math.random() * images.length);
        images.forEach((img, index) => {
            if (index === randomIndex) {
                img.style.display = "none";
            }
        });
    }

    const menuTriggerBtn = document.getElementById("menuTriggerBtn");
    if (menuTriggerBtn) {
        menuTriggerBtn.addEventListener("click", function() {
            let menu = document.getElementById("sideMenu");
            let icon = this.querySelector(".sideTooltip-target");

            if (menu.classList.contains("expanded")) {
                menu.classList.remove("expanded");
                icon.classList.remove("rotate");
                icon.classList.add("rotateBack");
                icon.classList.replace("fi-sr-grid", "fi-rr-grid");

                let tooltips = document.querySelectorAll('.sideTooltip');
                tooltips.forEach(tooltip => {
                    tooltip.classList.remove('visible');
                });

                setTimeout(() => {
                    icon.classList.remove("rotateBack");
                }, 400);
            } else {
                menu.classList.add("expanded");
                icon.classList.add("rotate");
                icon.classList.replace("fi-rr-grid", "fi-sr-grid");

                let tooltips = document.querySelectorAll('.sideTooltip');
                tooltips.forEach(tooltip => {
                    tooltip.classList.add('visible');
                });
            }
        });
    }
});
