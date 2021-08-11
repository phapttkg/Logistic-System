import { useEffect } from "react";

const RedirectHome = function() {
    useEffect( () => {
        window.location.assign('http://localhost:8080/login');
    }

    );
    return (
        <div>
        </div>
    )
};

export default RedirectHome;