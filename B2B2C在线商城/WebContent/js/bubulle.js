var timer = 300;
addCircles();

function addCircles() {
    setTimeout(() => {
        addCircle(...randomPosition());
        addCircles();
    }, timer);
}

function addCircle(x, y) {
    var circle = document.createElement('div');
    var animationTime = Math.round(Math.random() * 15);
    circle.classList.add('circle');
    circle.style.left = x + 'vw';
    circle.style.top = y + 'vh';
    circle.style.backgroundColor = randomColor();
    circle.style.setProperty('--grow-time', `${animationTime}s`);

    requestAnimationFrame(() => {
        document.body.appendChild(circle);
        setTimeout(removeCircle.bind(this, circle), animationTime * 1000);
    });
}

function removeCircle(circle) {
    document.body.removeChild(circle);
}

function randomPosition() {
    return [
        Math.random() * 100 + 1,
        Math.random() * 100 + 1
    ];

}

function randomColor() {
    const colors = [
        // '#88f8f8',
        // '#8fd3b8',
        'rgba(129, 255, 236, 0.55)',
        '#c7cbfe',

        // '#ffeaa7',
        'rgba(255, 230, 163, 0.55)',
        // '#fab1a0',
        '#rgba(255, 146, 141, 0.6)',
        '#ffc1ad',
        'rgba(255, 201, 161, 0.61)',
        // '#fda7d4'
        'rgba(255, 155, 223, 0.58)'
    ];
        // 'rgba(255, 255, 255, 0.25)'];


    return colors[Math.round(Math.random() * colors.length)];
    // return colors[colors*0.3];
}