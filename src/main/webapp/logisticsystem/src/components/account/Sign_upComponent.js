import axios from 'axios';
import Sign_upForm from './FormComponent'
import AdminMenu from './AdminMenuComponent'
axios.defaults.withCredentials = true;

//const HUB_REST_API_URL = 'http://localhost:8080/account/sign-up.do';

const Sign_upComponent = function(){
        return (    
            <div>
                <AdminMenu></AdminMenu>
                <Sign_upForm />
            </div>
        )
    }


export default Sign_upComponent