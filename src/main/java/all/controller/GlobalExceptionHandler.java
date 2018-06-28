//package all.controller;
//
//import lombok.experimental.FieldDefaults;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import static lombok.AccessLevel.PRIVATE;
//
//@FieldDefaults(level = PRIVATE)
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
//
//    @ExceptionHandler()
//    public ResponseEntity<String> handleResourceNotFound(HttpStatus ex){
//    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Input");
//    }
//
//
//
//}
